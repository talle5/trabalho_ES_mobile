package com.vezcerta.saudedigital.screens.perfil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vezcerta.saudedigital.ThemeBlue

/**
 * Cores usadas só na tela de perfil (tiradas do protótipo).
 * Ficam aqui para não mexer no tema global do app.
 */
internal object PerfilCores {
    val Fundo = Color(0xFFF7F8FF)
    val Card = Color.White
    val BordaCard = Color(0xFFDDE3EC)
    val Divisor = Color(0xFFE6EAF0)
    val AvatarFundo = Color(0xFFEAF2FF)
    val TextoPrincipal = Color(0xFF1A1A1A)
    val TextoSecundario = Color(0xFF6B7280)
}

/** Linha do topo: avatar com iniciais, nome, cidade e ícone de histórico. */
@Composable
internal fun PerfilHeader(
    paciente: Paciente,
    onHistoricoClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AvatarIniciais(iniciais = iniciais(paciente.nome))
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = paciente.nome,
                color = PerfilCores.TextoPrincipal,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "Paciente • ${paciente.cidade}",
                color = PerfilCores.TextoSecundario,
                fontSize = 14.sp,
            )
        }
        IconButton(onClick = onHistoricoClick) {
            Icon(
                imageVector = Icons.Outlined.AccessTime,
                contentDescription = "Histórico",
                tint = PerfilCores.TextoPrincipal,
                modifier = Modifier.size(32.dp),
            )
        }
    }
}

/** Círculo com as iniciais do nome. Depois dá para trocar por uma foto. */
@Composable
internal fun AvatarIniciais(
    iniciais: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(PerfilCores.AvatarFundo)
            .clearAndSetSemantics { },
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = iniciais,
            color = ThemeBlue,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

/** Cartão branco "Dados pessoais" com as linhas de rótulo e valor. */
@Composable
internal fun CartaoDadosPessoais(
    paciente: Paciente,
    modifier: Modifier = Modifier,
) {
    val dados = listOf(
        "CPF" to mascararCpf(paciente.cpf),
        "Data de nascimento" to paciente.dataNascimento,
        "Email" to paciente.email,
        "Telefone" to paciente.telefone,
        "Endereço" to paciente.endereco,
    )

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = PerfilCores.Card,
        shadowElevation = 2.dp,
        border = BorderStroke(1.dp, PerfilCores.BordaCard),
    ) {
        Column {
            Text(
                text = "Dados pessoais",
                color = ThemeBlue,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            )
            dados.forEach { (rotulo, valor) ->
                HorizontalDivider(color = PerfilCores.Divisor)
                DadoPessoalItem(rotulo = rotulo, valor = valor)
            }
        }
    }
}

/** Uma linha do cartão: rótulo pequeno cinza e valor logo abaixo. */
@Composable
internal fun DadoPessoalItem(
    rotulo: String,
    valor: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) { }
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            text = rotulo,
            color = PerfilCores.TextoSecundario,
            fontSize = 12.sp,
        )
        Text(
            text = valor,
            color = PerfilCores.TextoPrincipal,
            fontSize = 16.sp,
        )
    }
}
