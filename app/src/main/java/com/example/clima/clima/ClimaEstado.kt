package com.example.clima.clima

sealed class ClimaEstado {
    data object Vacio: ClimaEstado()
    data object Cargando: ClimaEstado()
//    data class Ok(val termica: Float) : ClimaEstado()
data class Ok (
    val ciudad: String = "",
    val temperatura: Double = 0.0,
    val descripcion: String= "",
    val st :Double = 0.0,
) : ClimaEstado()
    data class Error(val mensaje : String = ""): ClimaEstado()
}