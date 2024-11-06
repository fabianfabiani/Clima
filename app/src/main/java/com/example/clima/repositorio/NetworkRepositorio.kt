package com.example.clima.repositorio

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class NetworkRepositorio : Repositorio{
    private val cliente = HttpClient(){
        install(ContentNegotiation){
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }



    override suspend fun getTermica(): Float{
        val respuesta = cliente.get("https://api.bluelytics.com.ar/v2/latest")
        if(respuesta.status == HttpStatusCode.OK){
            var body = respuesta.body<RespuestaClimaModelo>()
            return body.blue.value_avg
        }
        else{
            throw Exception()
        }
    }
}

