package com.example.clima.repositorio

import kotlinx.serialization.Serializable

@Serializable
data class ClimaModelo (
    var value_avg: Float,
    var value_sell: Float,
    var value_buy: Float
)

@Serializable
data class RespuestaClimaModelo(
    var oficial: ClimaModelo,
    var blue: ClimaModelo,
    var oficial_euro: ClimaModelo,
    var blue_euro: ClimaModelo
)

