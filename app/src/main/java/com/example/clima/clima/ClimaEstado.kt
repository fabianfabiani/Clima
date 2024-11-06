package com.example.clima.clima

sealed class ClimaEstado {
    data object Vacio: ClimaEstado()
    data object Cargando: ClimaEstado()
    data class Ok(val termica: Float) : ClimaEstado()
    data object Error: ClimaEstado()
}