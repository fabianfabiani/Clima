package com.example.clima.clima

sealed class ClimaIntencion {
    data object cargarTermica: ClimaIntencion()
}