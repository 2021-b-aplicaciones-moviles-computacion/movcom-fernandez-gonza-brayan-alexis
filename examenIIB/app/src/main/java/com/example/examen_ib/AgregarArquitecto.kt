package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AgregarArquitecto : AppCompatActivity() {
    val db = Firebase.firestore
    val arquitectos = db.collection("Arquitectos")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_arquitecto)
    }

    override fun onStart() {
        super.onStart()

        val txt_input_nombreA = findViewById<TextInputEditText>(R.id.text_input_nombreArquitecto)
        val txt_input_cedulaA = findViewById<TextInputEditText>(R.id.txt_input_cedulaArquitecto)
        val txt_input_edadA = findViewById<TextInputEditText>(R.id.txt_input_salarioArquitecto)
        val btn_agregar_Arqui = findViewById<Button>(R.id.btn_guardar_arquitecto)

        btn_agregar_Arqui.setOnClickListener {
            var arquitecto = hashMapOf(
                "nombre" to txt_input_nombreA.text.toString(),
                "salario" to txt_input_edadA.text.toString()
            )
            arquitectos.add(arquitecto).addOnSuccessListener {
                Toast.makeText(this, "Ingreso correcto de datos", Toast.LENGTH_SHORT).show()
                Log.i("Agregar arquitecto", "Success")
            }.addOnFailureListener {
                Log.i("Agregar arquitecto", "Failed")
            }

            val intentAddSucces = Intent(this, MainActivity::class.java)
            startActivity(intentAddSucces)
        }
    }
}