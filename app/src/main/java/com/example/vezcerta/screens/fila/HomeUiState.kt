package com.example.vezcerta.screens.fila

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val message: String) : HomeUiState
    data class Error(val errorMessage: String) : HomeUiState
}
