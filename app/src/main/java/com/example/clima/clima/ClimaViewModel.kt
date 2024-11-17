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
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.concurrent.Executor

//class ClimaViewModel(repositorio: Repositorio) : ViewModel() {
//    var repositorio = NetworkRepositorio()
class ClimaViewModel(val repositorio: Repositorio) : ViewModel() {
    var estado by mutableStateOf<ClimaEstado>(ClimaEstado.Vacio)

    fun cargarClima(ciudad: String) {
        estado = ClimaEstado.Cargando
        viewModelScope.launch {
            try {
                // Busca la ciudad y obtiene sus coordenadas
                val ciudades = repositorio.buscarCiudad(ciudad)
                if (ciudades.isNotEmpty()) {
                    val ciudadSeleccionada = ciudades.first()
                    val clima = repositorio.traerClima(
                        lat = ciudadSeleccionada.lat,
                        lon = ciudadSeleccionada.lon
                    )

                    // Actualiza el estado con la información del clima
                    estado = ClimaEstado.Ok(
                        ciudad = clima.name,
                        temperatura = clima.main.temp,
                        descripcion = clima.weather.first().description,
                        st = clima.main.feels_like
                    )
                } else {
                    estado = ClimaEstado.Error("Ciudad no encontrada")
                }
            } catch (exception: Exception) {
                estado = ClimaEstado.Error(exception.localizedMessage ?: "Hubo un problema técnico")
            }
        }
    }

    class ClimaViewModelFactory(
        private val repositorio: Repositorio,
        //private val router: Router,
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

