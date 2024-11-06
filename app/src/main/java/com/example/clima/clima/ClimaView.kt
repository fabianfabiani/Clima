package com.example.clima.clima

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ClimaView(
    modifier: Modifier = Modifier,
    estado: ClimaEstado,
    ejecutar: (ClimaIntencion) -> Unit
) {
    Scaffold {
        Column (modifier = modifier.padding(it)){
            when(estado) {
                is ClimaEstado.Cargando -> Cargando()
                is ClimaEstado.Error -> Error()
                is ClimaEstado.Ok -> Clima(estado.termica)
                is ClimaEstado.Vacio -> Text(text = "")
            }
            Button(onClick = {ejecutar(ClimaIntencion.cargarTermica)}) {
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

@Composable
fun Clima(termica : Float){
    Text(text = "Temperatura del dia $termica",
        style = MaterialTheme.typography.titleLarge)
}