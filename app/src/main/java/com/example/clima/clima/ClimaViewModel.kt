package com.example.clima.clima

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
//import com.example.clima.repositorio.MockRepositorio
//import com.example.clima.repositorio.NetworkRepositorio
import com.example.clima.repositorio.Repositorio
import com.istea.appdelclima.repository.modelos.Ciudad
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.concurrent.Executor


class ClimaViewModel(private val repositorio: Repositorio) : ViewModel() {
    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)
        private set

    fun ejecutar(intencion: ClimaIntencion) {
        when (intencion) {
            is ClimaIntencion.BuscarCiudad -> buscarCiudad(intencion.nombreCiudad)
            is ClimaIntencion.CargarClima -> cargarClima(intencion.ciudad)
        }
    }

    private fun buscarCiudad(nombreCiudad: String) {
        estado = ClimaEstado.Cargando
        viewModelScope.launch {
            try {
                val ciudades = repositorio.buscarCiudad(nombreCiudad)
                // Transforma el estado para incluir las ciudades encontradas
                estado = ClimaEstado.CiudadesEncontradas(ciudades)
            } catch (exception: Exception) {
                estado = ClimaEstado.Error(exception.localizedMessage ?: "Error al buscar la ciudad")
            }
        }
    }

    private fun cargarClima(ciudad: Ciudad) {
        estado = ClimaEstado.Cargando
        viewModelScope.launch {
            try {
                val clima = repositorio.traerClima(lat = ciudad.lat, lon = ciudad.lon)
                estado = ClimaEstado.Ok(
                    ciudad = clima.name,
                    temperatura = clima.main.temp,
                    descripcion = clima.weather.first().description,
                    st = clima.main.feels_like
                )
            } catch (exception: Exception) {
                estado = ClimaEstado.Error(exception.localizedMessage ?: "Error al cargar el clima")
            }
        }
    }
    class ClimaViewModelFactory(private val repositorio: Repositorio) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
                return ClimaViewModel(repositorio) as T
            }
            throw IllegalArgumentException("Clase ViewModel desconocida")
        }
    }
}






//    class ClimaViewModelFactory(
//        private val repositorio: Repositorio,
//    ) : ViewModelProvider.Factory {
//        @Suppress("UNCHECKED_CAST")
//        override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            if (modelClass.isAssignableFrom(ClimaViewModel::class.java)) {
//                return ClimaViewModel(repositorio) as T
//            }
//            throw IllegalArgumentException("Unknown ViewModel class")
//        }
//    }

