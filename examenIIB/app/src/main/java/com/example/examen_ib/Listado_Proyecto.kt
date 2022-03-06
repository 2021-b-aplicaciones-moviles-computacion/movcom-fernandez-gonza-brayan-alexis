package com.example.examen_ib

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class Listado_Proyecto : AppCompatActivity() {
    var idItemSeleccionado = 0
    var arquitecto_owner = FirestoreArquitecto("","","")
    val db = Firebase.firestore
    val arquitectos = db.collection("Arquitectos")
    var adaptador: ArrayAdapter<FirestoreProyecto>? = null

    var resultAddNewProyecto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if (result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
                arquitecto_owner = intent.getParcelableExtra<FirestoreArquitecto>("Arquitecto")!!
            }
        }
    }

    var resultEditarProyecto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
                arquitecto_owner = intent.getParcelableExtra<FirestoreArquitecto>("Arquitecto")!!
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_proyecto)
    }

    override fun onStart() {
        super.onStart()
        //arquitecto_owner = intent.getParcelableExtra<FirestoreArquitecto>("Arquitecto")!!

        updateProyectosList()

        //val lbl_arquitecto_owner = findViewById<TextView>(R.id.tv_nombre_arquitecto)
        //lbl_arquitecto_owner.setText("Arquitecto: ${arquitecto_owner.arquitectoNombre}")

        val btn_add_proyecto = findViewById<Button>(R.id.btn_agregar_proyecto)
        btn_add_proyecto.setOnClickListener {
            val openAgregarProyecto = Intent(this, AgregarProyecto::class.java)
            openAgregarProyecto.putExtra("Arquitectos", arquitecto_owner)
            resultAddNewProyecto.launch(openAgregarProyecto)
        }

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_proyecto, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("id arquitecto por proyecto", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var proyectoSelected:FirestoreProyecto = adaptador!!.getItem(idItemSeleccionado)!!
        return when(item.itemId){
            R.id.mi_editar_proyecto -> {
                Log.i("context-menu","Edit position: ${idItemSeleccionado}")
                val openEditarProyecto = Intent(this, EditarProyecto::class.java)
                openEditarProyecto.putExtra("Arquitectos",arquitecto_owner)
                openEditarProyecto.putExtra("Arquitecto",proyectoSelected)
                resultEditarProyecto.launch(openEditarProyecto)
                return true
            }
            R.id.mi_eliminar_proyecto -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val arquitectoSubCollection = arquitectos.document("${arquitecto_owner.arquitectoId}")
                    .collection("Proyectos")
                    .document("${proyectoSelected.proyectoId}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-PROYECTO", "Success")
                    }
                    .addOnFailureListener {
                        Log.i("DELETE-PROYECTO","Failure")
                    }
                updateProyectosList()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun updateProyectosList(){
        val arquitectoSubCollection = arquitectos.document("${arquitecto_owner.arquitectoId}")
            .collection("Proyectos")
            .whereEqualTo("arquitectoProyecto","${arquitecto_owner.arquitectoId}")

        val lv_proyecto_list = findViewById<ListView>(R.id.lv_list_view_lista_proyectos)

        arquitectoSubCollection.get().addOnSuccessListener { result ->
            var proyectosList = arrayListOf<FirestoreProyecto>()

            for (document in result){
                proyectosList.add(
                    FirestoreProyecto(
                        document.id.toString(),
                        document.data.get("nombreProyecto").toString(),
                        document.data.get("costo").toString(),
                        document.data.get("arquitectoProyecto").toString(),
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                proyectosList
            )

            lv_proyecto_list.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lv_proyecto_list)
        }
    }
}