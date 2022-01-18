package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Switch
import com.google.android.material.textfield.TextInputEditText

class AgregarArquitecto : AppCompatActivity() {
    var nextID = 0
    var lastID = 0
    var nombre = ""
    var salario = ""
    var cedula = 0
    var afiliacion = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_arquitecto)
    }

    override fun onStart() {
        super.onStart()
        var lengthListado_Arquitecto = BBaseDatosMemoria.arregloArquitectos.lastIndex

        BBaseDatosMemoria.arregloArquitectos.forEachIndexed { index: Int, arquitecto: BArquitecto ->
            if (index == lengthListado_Arquitecto){
                lastID = arquitecto.cedulaArquitecto
            }
        }

        var status = "false";

        var txt_input_nombreA = findViewById<TextInputEditText>(R.id.text_input_nombreArquitecto)
        var txt_input_cedulaA = findViewById<TextInputEditText>(R.id.txt_input_cedulaArquitecto)
        var txt_input_edadA = findViewById<TextInputEditText>(R.id.txt_input_salarioArquitecto)
        var btn_agregar_Arqui = findViewById<Button>(R.id.btn_guardar_arquitecto)
        var swt_estado_Afiliacion = findViewById<Switch>(R.id.swEstadoAfiliacion)


        btn_agregar_Arqui.setOnClickListener {
            nombre = txt_input_nombreA.text.toString()
            cedula = Integer.parseInt(txt_input_cedulaA.text.toString())
            salario = txt_input_edadA.text.toString()
            swt_estado_Afiliacion.setOnClickListener {
                if (swt_estado_Afiliacion.isChecked){
                    afiliacion = "true"
                }else{
                    afiliacion = "false"
                }
            }
            BBaseDatosMemoria.arregloArquitectos.add(
                BArquitecto(cedula, nombre, salario,afiliacion)
            )
            val intentAddSucces = Intent(this, MainActivity::class.java)
            startActivity(intentAddSucces)
        }
    }
}