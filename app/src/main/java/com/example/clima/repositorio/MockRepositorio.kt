package com.example.clima.repositorio

import kotlinx.coroutines.delay

class MockRepositorio : Repositorio{
    override suspend fun getTermica(): Float {
        delay(2000)
        return 1000.0f
    }
}