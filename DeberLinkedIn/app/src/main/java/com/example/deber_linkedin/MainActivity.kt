package com.example.deber_linkedin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber_linkedin.adapter.EmpleoAdapter
import com.example.deber_linkedin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }

    private fun initRecyclerView(){
        val manager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this,manager.orientation )
        binding.recyclerEmpleos.layoutManager = manager
        binding.recyclerEmpleos.adapter = EmpleoAdapter(EmpleoProvider.empleosList)
        binding.recyclerEmpleos.addItemDecoration(decoration)
    }

}