package com.example.clima.clima

import com.istea.appdelclima.repository.modelos.Ciudad

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
    data class CiudadesEncontradas(val ciudades: List<Ciudad>) : ClimaEstado()
    data class Error(val mensaje : String = ""): ClimaEstado()
}
