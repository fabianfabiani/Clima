package com.example.clima.clima

import com.istea.appdelclima.repository.modelos.Ciudad

sealed class ClimaIntencion {
    data class BuscarCiudad(val nombreCiudad: String) : ClimaIntencion()
    data class CargarClima(val ciudad: Ciudad): ClimaIntencion()
}