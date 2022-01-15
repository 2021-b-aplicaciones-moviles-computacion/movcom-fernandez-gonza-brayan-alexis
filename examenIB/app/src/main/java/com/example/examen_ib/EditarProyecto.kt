package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarProyecto : AppCompatActivity() {
    var index_Arquitecto = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_proyecto)
    }

    override fun onStart() {
        super.onStart()


        val idProyecto = intent.getIntExtra("numProyecto",1)
        index_Arquitecto = intent.getIntExtra("posicionArquitectoEditar",1)

        val txt_nombreEditarProyecto = findViewById<TextInputEditText>(R.id.txt_input_newNombreProyecto)
        val txt_costoEditarProyecto = findViewById<TextInputEditText>(R.id.txt_input_newCostoProyecto)

        //val objArquitecto = BBaseDatosMemoria.arregloArquitecto_Proyecto.filter { it.numProyecto == objProyecto.numProyecto }
        BBaseDatosMemoria.arregloProyectos.forEachIndexed { index: Int, proyecto: BProyecto ->
            if(idProyecto == proyecto.numProyecto){
                txt_nombreEditarProyecto.setText(proyecto.nombreProyecto)
                txt_costoEditarProyecto.setText(proyecto.costo)
            }
        }

        val btn_GuardarCambiosProyecto = findViewById<Button>(R.id.btn_guardar_editar_proyecto)
        btn_GuardarCambiosProyecto.setOnClickListener {
            BBaseDatosMemoria.arregloProyectos.forEachIndexed { index: Int, proyecto: BProyecto ->
                if(idProyecto == proyecto.numProyecto){
                    proyecto.nombreProyecto = txt_nombreEditarProyecto.text.toString()
                    proyecto.costo = txt_costoEditarProyecto.text.toString()
                }
            }
            devolverRespuesta()
        }

    }
    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionArquitectoEditar", index_Arquitecto)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}