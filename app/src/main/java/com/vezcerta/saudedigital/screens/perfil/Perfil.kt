package com.vezcerta.saudedigital.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vezcerta.saudedigital.ThemeBlue
import com.vezcerta.saudedigital.ui.theme.VezCertaTheme

@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier,
    // Por enquanto usa os dados de exemplo; depois é só passar o estado real.
    uiState: PerfilUiState = PerfilUiState.Success(PacienteExemplo),
    onHistoricoClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(PerfilCores.Fundo)
    ) {
        when (uiState) {
            PerfilUiState.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = ThemeBlue,
            )

            is PerfilUiState.Error -> Text(
                text = uiState.errorMessage,
                color = PerfilCores.TextoSecundario,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(24.dp),
            )

            is PerfilUiState.Success -> PerfilConteudo(
                paciente = uiState.paciente,
                onHistoricoClick = onHistoricoClick,
            )
        }
    }
}

@Composable
private fun PerfilConteudo(
    paciente: Paciente,
    onHistoricoClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Text(
            text = "Meu perfil",
            color = PerfilCores.TextoPrincipal,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))

        PerfilHeader(
            paciente = paciente,
            onHistoricoClick = onHistoricoClick,
        )
        Spacer(modifier = Modifier.height(24.dp))

        CartaoDadosPessoais(paciente = paciente)
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Mantenha seus dados atualizados para facilitar seu atendimento.",
            color = PerfilCores.TextoSecundario,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        )
    }
}

@Preview(showBackground = true, widthDp = 393, heightDp = 700)
@Composable
private fun PerfilScreenPreview() {
    VezCertaTheme {
        PerfilScreen()
    }
}
