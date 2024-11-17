package com.example.clima.clima

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.clima.repositorio.RepositorioApi


@Composable
fun ClimaPage() {
    val viewModel: ClimaViewModel = viewModel(
        factory = ClimaViewModel.ClimaViewModelFactory(repositorio = RepositorioApi())
    )

    ClimaView(
        estado = viewModel.estado,
        ejecutar = { intencion -> viewModel.ejecutar(intencion) }
    )
}

