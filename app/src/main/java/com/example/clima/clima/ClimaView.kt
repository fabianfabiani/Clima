package com.example.clima.clima

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.istea.appdelclima.repository.modelos.Ciudad


@Composable
fun ClimaView(
    estado: ClimaEstado,
    ejecutar: (ClimaIntencion) -> Unit
) {
    var ciudadInput by remember { mutableStateOf("") }

    Scaffold { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding) // Aplica el padding aquí
        ) {
            TextField(
                value = ciudadInput,
                onValueChange = { ciudadInput = it },
                label = { Text("Ingrese la ciudad") }
            )

            Button(onClick = { ejecutar(ClimaIntencion.BuscarCiudad(ciudadInput)) }) {
                Text(text = "Buscar ciudad")
            }

            when (estado) {
                is ClimaEstado.Cargando -> Text(text = "Cargando...")
                is ClimaEstado.Error -> Text(text = "Error: ${estado.mensaje}")
                is ClimaEstado.CiudadesEncontradas -> {
                    estado.ciudades.forEach { ciudad ->
                        Button(onClick = { ejecutar(ClimaIntencion.CargarClima(ciudad)) }) {
                            Text(text = ciudad.name)
                        }
                    }
                }
                is ClimaEstado.Ok -> {
                    Text(text = "Ciudad: ${estado.ciudad}")
                    Text(text = "Temperatura: ${estado.temperatura}°C")
                    Text(text = "Descripción: ${estado.descripcion}")
                    Text(text = "Sensación Térmica: ${estado.st}°C")
                }
                ClimaEstado.Vacio -> Text(text = "Ingrese una ciudad para comenzar")
            }
        }
    }
}




@Composable
fun Cargando(){
    Text(text = "Cargando")
}

@Composable
fun Vacio(){

}

@Composable
fun Error(){

}

//@Composable
//fun Clima(termica : Float){
//    Text(text = "Temperatura del dia $termica",
//        style = MaterialTheme.typography.titleLarge)
//}

@Composable
fun ClimaView(ciudad: String, temperatura: Double, descripcion: String, st:Double){
    Column {
        Text(text = ciudad, style = MaterialTheme.typography.titleMedium)
        Text(text = "${temperatura}°", style = MaterialTheme.typography.titleLarge)
        Text(text = descripcion, style = MaterialTheme.typography.bodyMedium)
        Text(text = "sensacionTermica: ${st}°", style = MaterialTheme.typography.bodyMedium)
    }
}