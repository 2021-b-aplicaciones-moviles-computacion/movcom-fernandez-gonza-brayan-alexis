package com.example.deber_linkedin.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deber_linkedin.Empleo
import com.example.deber_linkedin.databinding.ItemEmpleoBinding

class EmpleoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    var binding = ItemEmpleoBinding.bind(view)

    fun render(empleo: Empleo){
        binding.tvEmpleoCargo.text = empleo.cargo
        binding.tvEmpleoEmpresa.text = empleo.empresa
        binding.tvEmpleoCiudad.text = empleo.ciudad

        Glide.with(binding.ivEmpleo.context).load(empleo.photo).into(binding.ivEmpleo)

        binding.ivEmpleo.setOnClickListener {
            Toast.makeText(
                binding.ivEmpleo.context,
                empleo.cargo,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}