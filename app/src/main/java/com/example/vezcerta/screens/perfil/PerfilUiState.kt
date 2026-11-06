package com.example.vezcerta.screens.perfil

/** Dados do paciente exibidos na tela de perfil. */
data class Paciente(
    val nome: String,
    val cidade: String,
    val cpf: String,
    val dataNascimento: String,
    val email: String,
    val telefone: String,
    val endereco: String,
)

/** Mesmo padrão do HomeUiState: carregando, sucesso ou erro. */
sealed interface PerfilUiState {
    data object Loading : PerfilUiState
    data class Success(val paciente: Paciente) : PerfilUiState
    data class Error(val errorMessage: String) : PerfilUiState
}

/**
 * Dados de exemplo (iguais ao protótipo). Provisório: quando o app for
 * ligado ao backend, esse objeto deixa de ser usado.
 */
internal val PacienteExemplo = Paciente(
    nome = "João da Silva",
    cidade = "Quixadá – CE",
    cpf = "12345678900",
    dataNascimento = "15/05/1985",
    email = "joao.silva@email.com",
    telefone = "(88) 99999-1234",
    endereco = "Rua José de Alencar, 245\nCentro, Quixadá – CE",
)

/** Esconde os 3 primeiros e os 2 últimos dígitos: 12345678900 -> ***.456.789-** */
internal fun mascararCpf(cpf: String): String {
    val digitos = cpf.filter { it.isDigit() }
    if (digitos.length != 11) return "***.***.***-**"
    return "***.${digitos.substring(3, 6)}.${digitos.substring(6, 9)}-**"
}

/** Primeira letra do primeiro e do último nome: "João da Silva" -> "JS" */
internal fun iniciais(nome: String): String {
    val partes = nome.trim().split(" ").filter { it.isNotBlank() }
    return when (partes.size) {
        0 -> "?"
        1 -> partes.first().take(1).uppercase()
        else -> (partes.first().take(1) + partes.last().take(1)).uppercase()
    }
}
