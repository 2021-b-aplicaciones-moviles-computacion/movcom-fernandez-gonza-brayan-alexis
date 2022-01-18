package com.example.examen_ib

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts

class Listado_Proyecto : AppCompatActivity() {
    var idItemSeleccionado = 0
    var idArquitectoACargo = 0
    var posicionArquitecto = 0
    var lista_proyecto = arrayListOf<String>()

    var resultAddNewProyecto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if (result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
                posicionArquitecto = data?.getIntExtra("posicionArquitecto",0)!!
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
                posicionArquitecto = data?.getIntExtra("posicionArquitectoAEditar", 0)!!
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_proyecto)
    }

    override fun onStart() {
        super.onStart()
        lista_proyecto = arrayListOf()
        posicionArquitecto = intent.getIntExtra("posicionEditar",1)
        var idProyecto = arrayListOf<Int>()
        val tvNombreArquitecto = findViewById<TextView>(R.id.tv_nombre_arquitecto)

        BBaseDatosMemoria.arregloArquitectos.forEachIndexed { index: Int, arquitecto: BArquitecto ->
            if (index == posicionArquitecto){
                idArquitectoACargo = arquitecto.cedulaArquitecto
                tvNombreArquitecto.setText("${arquitecto.nombreArquitecto}")
            }
        }

        BBaseDatosMemoria.arregloArquitecto_Proyecto.forEachIndexed { index: Int, arquitectoProyecto: BArquitecto_Proyecto ->
            if (idArquitectoACargo == arquitectoProyecto.cedulaArquitecto){
                idProyecto.add(arquitectoProyecto.numProyecto)
            }
        }

        idProyecto.forEach { idProyecto: Int ->
            BBaseDatosMemoria.arregloProyectos.forEachIndexed { index: Int, proyecto: BProyecto ->
                if(idProyecto == proyecto.numProyecto){
                    lista_proyecto.add(proyecto.nombreProyecto.toString())
                }
            }
        }

        val listViewProyecto = findViewById<ListView>(R.id.lv_list_view_lista_proyectos)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            lista_proyecto
        )
        listViewProyecto.adapter = adaptador
        adaptador.notifyDataSetChanged()


        val btnAgregarProyecto = findViewById<Button>(R.id.btn_agregar_proyecto)
        btnAgregarProyecto.setOnClickListener {
            abrirActividadAgregarProyecto(AgregarProyecto::class.java)
        }

        this.registerForContextMenu(listViewProyecto)


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
        var nombreProyecto: String = lista_proyecto.elementAt(id)
        BBaseDatosMemoria.arregloProyectos.forEach { proyecto: BProyecto ->
            if(nombreProyecto == proyecto.nombreProyecto){
                idItemSeleccionado = proyecto.numProyecto
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar_proyecto -> {
                abrirActividadEditarProyecto(EditarProyecto::class.java)
                return true
            }
            R.id.mi_eliminar_proyecto -> {
                eliminarProyecto(idItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarProyecto(
        clase: Class<*>
    ){
        val intentEditarProyecto = Intent(this, clase)
        intentEditarProyecto.putExtra("numProyecto",idItemSeleccionado)
        intentEditarProyecto.putExtra("posicionArquitectoEditar",posicionArquitecto)
        resultEditarProyecto.launch(intentEditarProyecto)
    }

    fun abrirActividadAgregarProyecto(
        clase: Class<*>
    ){
        val intentAgregarProyecto = Intent(this,clase)
        intentAgregarProyecto.putExtra("posicionArquitecto",posicionArquitecto)
        resultAddNewProyecto.launch(intentAgregarProyecto)
    }

    fun eliminarProyecto(
        numProyectoAEliminar: Int
    ){
        val listViewProyecto = findViewById<ListView>(R.id.lv_list_view_lista_proyectos)
        var nombreProyectoAEliminar = ""
        BBaseDatosMemoria.arregloProyectos.forEach { proyecto: BProyecto ->
            if(numProyectoAEliminar == proyecto.numProyecto){
                nombreProyectoAEliminar = proyecto.nombreProyecto.toString()
            }
        }

        var demas_Proyectos = arrayListOf<BArquitecto_Proyecto>()
        BBaseDatosMemoria.arregloArquitecto_Proyecto.forEachIndexed { index: Int, arquitectoProyecto: BArquitecto_Proyecto ->
            if(!((numProyectoAEliminar == arquitectoProyecto.numProyecto) && (idArquitectoACargo == arquitectoProyecto.cedulaArquitecto))){
                demas_Proyectos.add(arquitectoProyecto)
            }
        }

        BBaseDatosMemoria.arregloArquitecto_Proyecto = demas_Proyectos
        lista_proyecto.remove(nombreProyectoAEliminar)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            lista_proyecto
        )
        listViewProyecto.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}