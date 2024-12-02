package com.example.cliente

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.cliente.databinding.ActivityLoginBinding
import com.example.cliente.poko.LoginCliente
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class LoginActivity : AppCompatActivity() {


    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.btnLogin.setOnClickListener {
            val correo = binding.etCorreo.text.toString()
            val password = binding.etPassword.text.toString()
            if(validarCampos(correo,password)){
                verificarCampos(correo,password)
            }
        }
    }

    fun validarCampos(correo:String, password: String): Boolean{
        var camposValidos = true
        if (correo.isEmpty()) {
            camposValidos = false
            binding.etCorreo.setError("correo obligatorio")
        }
        if (correo.isEmpty()) {
            camposValidos = false
            binding.etPassword.setError("contraseña obligatoria")
        }
        return camposValidos
    }

    fun verificarCampos(correo:String, password: String){
        //solo se ocupa una vez
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)
        //Consumo de WS
        Ion.with(this@LoginActivity)
            .load("POST", "http://10.30.11.80:8084/API/api/login/cliente")
            .setHeader("Content-Type","application/x-www-form-urlencoded")
            .setBodyParameter("correo",correo)
            .setBodyParameter("password", password)
            .asString()
            .setCallback { e, result ->
                if(e==null){
                    serializarInformacion(result)
                }else{
                    Toast.makeText(this@LoginActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }

    }
    fun serializarInformacion(json: String){
        val gson = Gson()
        val respuestaLoginCliente = gson.fromJson(json,LoginCliente::class.java)
        Toast.makeText(this@LoginActivity, respuestaLoginCliente.mensaje,Toast.LENGTH_LONG).show()
        if(!respuestaLoginCliente.error){
            val clienteJSON = gson.toJson(respuestaLoginCliente.cliente)
            irPantallaPrincipal(clienteJSON)
        }

    }

    fun irPantallaPrincipal(cliente: String){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("cliente", cliente)
        startActivity(intent)
        finish()
    }
}