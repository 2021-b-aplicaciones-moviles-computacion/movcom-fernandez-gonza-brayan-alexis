package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class EditarArquitecto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_arquitecto)
    }

    override fun onStart() {
        super.onStart()

        val posicionArquitecto = intent.getIntExtra("posicionEditar",1)

        val txtNombreAEditar = findViewById<TextInputEditText>(R.id.txt_input_newNombreArquitecto)
        val txtSalarioAEditar = findViewById<TextInputEditText>(R.id.txt_input_newEdadArquitecto)

        BBaseDatosMemoria.arregloArquitectos.forEachIndexed { index: Int, arquitecto: BArquitecto ->
            if(index == posicionArquitecto){
                txtNombreAEditar.setText(arquitecto.nombreArquitecto)
                txtSalarioAEditar.setText(arquitecto.salario)
            }
        }

        val btnGuardarCambiosArquit = findViewById<Button>(R.id.btn_guardar_editar_arquitecto)
        btnGuardarCambiosArquit.setOnClickListener {
            BBaseDatosMemoria.arregloArquitectos.forEachIndexed { index: Int, arquitecto: BArquitecto ->
                if(index == posicionArquitecto){
                    arquitecto.nombreArquitecto = (txtNombreAEditar.text.toString())
                    arquitecto.salario = txtSalarioAEditar.text.toString()
                }
            }
            val intentEditSuccess = Intent(this, MainActivity::class.java)
            startActivity(intentEditSuccess)
        }
    }
}