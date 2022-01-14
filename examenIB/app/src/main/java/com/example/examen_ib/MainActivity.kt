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

class MainActivity : AppCompatActivity() {
    var idItemSeleccionado = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listViewArquitectos = findViewById<ListView>(R.id.lv_list_view_arquitectos)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDatosMemoria.arregloArquitectos
        )
        listViewArquitectos.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewArquitectos)

        val btnAgregarArquitecto = findViewById<Button>(R.id.btn_agregar_arquitecto)
        btnAgregarArquitecto.setOnClickListener {
            val intentAddArquitecto = Intent(this, AgregarArquitecto::class.java)
            startActivity(intentAddArquitecto)
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

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentEditarArquitecto = Intent(this,clase)
        intentEditarArquitecto.putExtra("posicionEditar",idItemSeleccionado)
        startActivity(intentEditarArquitecto)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.mi_editar -> {
                Log.i("context-menu","Edit position: ${idItemSeleccionado}")
                abrirActividadConParametros(EditarArquitecto::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu","Delete position: ${idItemSeleccionado}")
                eliminarArquitecto(idItemSeleccionado)
                return true
            }
            R.id.mi_ver_proyectos -> {
                Log.i("context-menu","Proyectos: ${idItemSeleccionado}")
                abrirActividadConParametros(Listado_Proyecto::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun eliminarArquitecto(
        posicionArquitectoAEliminar: Int
    ){
        val listViewArquitecto = findViewById<ListView>(R.id.lv_list_view_arquitectos)

        var arquitectoAEliminar = BBaseDatosMemoria.arregloArquitectos.elementAt(posicionArquitectoAEliminar)
        var idArquitectoAEliminar = arquitectoAEliminar.cedulaArquitecto
        var auxListaArquitectoProyecto = arrayListOf<BArquitecto_Proyecto>()

        BBaseDatosMemoria.arregloArquitecto_Proyecto.forEachIndexed { index: Int, arquitectoProyecto: BArquitecto_Proyecto ->
            if (idArquitectoAEliminar != arquitectoProyecto.cedulaArquitecto){
                auxListaArquitectoProyecto.add(arquitectoProyecto)
            }
        }

        BBaseDatosMemoria.arregloArquitectos.removeAt(posicionArquitectoAEliminar)
        BBaseDatosMemoria.arregloArquitecto_Proyecto = auxListaArquitectoProyecto

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDatosMemoria.arregloArquitectos
        )
        listViewArquitecto.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}