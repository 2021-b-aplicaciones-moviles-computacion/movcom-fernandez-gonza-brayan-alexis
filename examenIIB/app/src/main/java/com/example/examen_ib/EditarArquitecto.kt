package com.example.examen_ib

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarArquitecto : AppCompatActivity() {

    var arquitecto_update = FirestoreArquitecto("","","")
    val db = Firebase.firestore
    val arquitectos = db.collection("Arquitectos")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_arquitecto)

    }

    override fun onStart() {
        super.onStart()

        arquitecto_update = intent.getParcelableExtra<FirestoreArquitecto>("Arquitecto")!!

        val in_arquitecto_nombre = findViewById<EditText>(R.id.txt_input_newNombreArquitecto)
        in_arquitecto_nombre.setText(arquitecto_update.arquitectoNombre.toString())

        val in_arquitecto_salario = findViewById<EditText>(R.id.txt_input_newEdadArquitecto)
        in_arquitecto_salario.setText(arquitecto_update.salario.toString())


        val btnGuardarCambiosArquit = findViewById<Button>(R.id.btn_guardar_editar_arquitecto)
        btnGuardarCambiosArquit.setOnClickListener {
            arquitectos.document("${arquitecto_update.arquitectoId}").update(
                "nombre", in_arquitecto_nombre.text.toString(),
                "salario",in_arquitecto_salario.text.toString()
            )
            Toast.makeText(this, "Cambios guardados", Toast.LENGTH_SHORT).show()
        }
    }
}