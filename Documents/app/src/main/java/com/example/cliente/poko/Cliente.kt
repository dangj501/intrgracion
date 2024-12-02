package com.example.cliente.poko



data class Cliente(
    val idCliente: Int,
    val nombre: String,
    val apellidoPaterno: String,
    val apellidoMaterno: String,
    val fechaNacimiento: String,
    val telefono: String,
    val peso: Float,
    val estatura: Int,
    val correo: String,
    var password: String?,
    val idRol: Int,
    var idColaborador: Int?,
    var entrenador: String?,
    var rol: String?,
    var fotoBase64: String?
)