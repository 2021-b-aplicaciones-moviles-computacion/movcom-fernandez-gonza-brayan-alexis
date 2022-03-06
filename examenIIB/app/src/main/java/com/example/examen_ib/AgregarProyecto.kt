package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class AgregarProyecto : AppCompatActivity() {
    var nextID = 0
    var lastID = 0
    var nombre = ""
    var costo = ""
    var posicionArquitecto = 0
    var idArquitectoACargo = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_proyecto)
    }

    override fun onStart() {
        super.onStart()
        posicionArquitecto = intent.getIntExtra("posicionArquitecto", -1)
        BBaseDatosMemoria.arregloArquitectos.forEachIndexed { index: Int, arquitecto: BArquitecto ->
            if(index == posicionArquitecto){
                idArquitectoACargo = arquitecto.cedulaArquitecto
            }
        }

        var lengthListaProyecto = BBaseDatosMemoria.arregloProyectos.lastIndex
        BBaseDatosMemoria.arregloProyectos.forEachIndexed { index: Int, proyecto: BProyecto ->
            if(index == lengthListaProyecto){
                lastID = proyecto.numProyecto
            }
        }

        nextID = lastID + 1

        var txtNombreProyecto = findViewById<TextInputEditText>(R.id.txt_input_nombreProyecto)
        var txtCostoProyecto = findViewById<TextInputEditText>(R.id.txt_input_costoProyecto)

        var btn_Guardar_Proyecto = findViewById<Button>(R.id.btn_guardar_proyecto)
        btn_Guardar_Proyecto.setOnClickListener {
            nombre = txtNombreProyecto.text.toString()
            costo = txtCostoProyecto.text.toString()
            BBaseDatosMemoria.arregloProyectos.add(
                BProyecto(nextID, nombre,costo)
            )
            BBaseDatosMemoria.arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(idArquitectoACargo, nextID)
            )
            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionArquitecto", posicionArquitecto)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }
}