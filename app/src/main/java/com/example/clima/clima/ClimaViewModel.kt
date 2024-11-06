package com.example.clima.clima

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.clima.repositorio.MockRepositorio
import com.example.clima.repositorio.NetworkRepositorio
import com.example.clima.repositorio.Repositorio
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ClimaViewModel(repositorio: Repositorio) : ViewModel() {
    var repositorio = NetworkRepositorio()
    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    fun ejecutar(intencion: ClimaIntencion) {
        when (intencion) {
            ClimaIntencion.cargarTermica -> cargarTermica()
        }
    }

    private fun cargarTermica() {
        estado = ClimaEstado.Cargando
        viewModelScope.launch {
            val termica = repositorio.getTermica()
            estado = ClimaEstado.Ok(termica)
        }
    }

    class ClimaViewModelFactory(
        private val repositorio: Repositorio,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
                return ClimaViewModel(repositorio) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}