package com.example.clima.clima

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clima.repositorio.NetworkRepositorio

@Composable
fun ClimaPage(){
    val viewModel : ClimaViewModel = viewModel(
        factory = ClimaViewModel.ClimaViewModelFactory(
            repositorio = NetworkRepositorio()
        )
    )
    ClimaView(estado = viewModel.estado){
        viewModel.ejecutar(it)
    }
}