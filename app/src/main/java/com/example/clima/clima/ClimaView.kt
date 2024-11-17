package com.example.clima.clima

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

@Composable
fun ClimaView(
    modifier: Modifier = Modifier,
    estado: ClimaEstado,
    ejecutar: (String) -> Unit
) {
    var ciudad by remember { mutableStateOf("") }

    Scaffold {
        Column(modifier = modifier.padding(it)) {
            when (estado) {
                is ClimaEstado.Cargando -> Cargando()
                is ClimaEstado.Error -> Error()
                is ClimaEstado.Ok -> ClimaView(
                    ciudad = estado.ciudad,
                    temperatura = estado.temperatura,
                    descripcion = estado.descripcion,
                    st = estado.st
                )
                is ClimaEstado.Vacio -> Text(text = "")
            }

            // Campo de texto para ingresar la ciudad
            TextField(
                value = ciudad,
                onValueChange = { ciudad = it },
                label = { Text("Ingrese la ciudad") }
            )

            Button(onClick = { ejecutar(ciudad) }) {
                Text(text = "Refrescar")
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