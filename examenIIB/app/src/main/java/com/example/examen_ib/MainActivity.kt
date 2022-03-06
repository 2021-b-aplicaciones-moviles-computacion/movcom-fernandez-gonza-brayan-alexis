package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val db = Firebase.firestore
    val arquitectos = db.collection("Arquitectos")
    var idItemSeleccionado = -1
    var adaptador: ArrayAdapter<FirestoreArquitecto>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        super.onStart()

        updateArquitectosList()

        val btn_agregar_arquitecto = findViewById<Button>(R.id.btn_agregar_arquitecto)
        btn_agregar_arquitecto.setOnClickListener {
            val intent = Intent(this, AgregarArquitecto::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        var arquitectoSelected:FirestoreArquitecto = adaptador!!.getItem(idItemSeleccionado)!!
        return when(item.itemId){
            R.id.mi_editar -> {
                Log.i("context-menu","Edit position: ${arquitectoSelected.arquitectoId}")
                val openEditarArquitecto = Intent(this, EditarArquitecto::class.java)
                openEditarArquitecto.putExtra("Arquitecto", arquitectoSelected)
                startActivity(openEditarArquitecto)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${arquitectoSelected.arquitectoId}")

                arquitectos.document("${arquitectoSelected.arquitectoId}").delete()
                    .addOnSuccessListener {
                        Log.i("DELETE-ARQUITECTO", "Success")
                    }
                    .addOnFailureListener {
                        Log.i("DELETE-ARQUITECTO","Failure")
                    }
                updateArquitectosList()
                return true
            }
            R.id.mi_ver_proyectos -> {
                Log.i("context-menu","Proyectos: ${idItemSeleccionado}")
                val openProyectsList = Intent(this, Listado_Proyecto::class.java)
                //openProyectsList.putExtra("Arquitecto",arquitectoSelected)
                startActivity(openProyectsList)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    fun updateArquitectosList(){
        val lb_arquitectos = findViewById<ListView>(R.id.lv_list_view_arquitectos)

        arquitectos.get().addOnSuccessListener { result ->
            var arquitectosList = arrayListOf<FirestoreArquitecto>()

            for (document in result){
                arquitectosList.add(
                    FirestoreArquitecto(
                        document.id.toString(),
                        document.get("nombre").toString(),
                        document.get("salario").toString()
                    )
                )
            }

            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                arquitectosList
            )
            lb_arquitectos.adapter = adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(lb_arquitectos)
        }.addOnFailureListener {
            Log.i("Error", "Failure retrieving arquitects")
        }
    }
}