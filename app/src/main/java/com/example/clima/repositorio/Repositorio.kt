package com.example.clima.repositorio

interface Repositorio {
    suspend fun getTermica() : Float
}