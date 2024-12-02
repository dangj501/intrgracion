package com.example.cliente

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import com.example.cliente.databinding.ActivityMainBinding
import com.example.cliente.poko.Cliente
import com.google.gson.Gson

private lateinit var binding: ActivityMainBinding
private lateinit var cliente: Cliente

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate((layoutInflater))
        val view = binding.root
        setContentView(R.layout.activity_main)
        obtenerDatosCliente()
        cargarDatosCliente()
    }
    fun obtenerDatosCliente(){
        val jsonCliente = intent.getStringExtra("cliente")
        if(jsonCliente != null){
            val gson = Gson()
            cliente = gson.fromJson(jsonCliente, Cliente::class.java)
        }
    }

    fun cargarDatosCliente(){
        binding.tvCliente.text = cliente.nombre + " " + cliente.apellidoPaterno + " " + cliente.apellidoPaterno
    }
}