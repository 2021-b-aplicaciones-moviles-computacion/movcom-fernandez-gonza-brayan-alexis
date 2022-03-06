package com.example.deber_linkedin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber_linkedin.Empleo
import com.example.deber_linkedin.R

class EmpleoAdapter(private val empleosList:List<Empleo>) : RecyclerView.Adapter<EmpleoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpleoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return EmpleoViewHolder(layoutInflater.inflate(R.layout.item_empleo, parent, false))
    }

    override fun onBindViewHolder(holder: EmpleoViewHolder, position: Int) {
        val item = empleosList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = empleosList.size
}