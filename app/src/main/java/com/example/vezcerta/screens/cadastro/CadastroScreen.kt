package com.example.vezcerta.screens.cadastro

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vezcerta.screens.login.BannerLogo
import com.example.vezcerta.screens.login.BotaoAzul
import com.example.vezcerta.screens.login.CampoComRotulo
import com.example.vezcerta.ui.theme.VezCertaTheme

@Composable
fun CadastroScreen(
    onEfetuarCadastro: (nome: String, cpf: String, cartaoSus: String, email: String, senha: String) -> Unit,
    modifier: Modifier = Modifier
) {
    var nome by rememberSaveable { mutableStateOf("") }
    var cpf by rememberSaveable { mutableStateOf("") }
    var cartaoSus by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }

    var erroNome by remember { mutableStateOf<String?>(null) }
    var erroCpf by remember { mutableStateOf<String?>(null) }
    var erroSus by remember { mutableStateOf<String?>(null) }
    var erroEmail by remember { mutableStateOf<String?>(null) }
    var erroSenha by remember { mutableStateOf<String?>(null) }

    fun efetuarCadastro() {
        erroNome = if (nome.isBlank()) "Informe seu nome completo." else null
        erroCpf = if (cpf.length != 11) "O CPF tem 11 dígitos." else null
        erroSus = if (cartaoSus.length != 15) "O Cartão SUS tem 15 dígitos." else null
        erroEmail = when {
            email.isBlank() -> "Informe seu e-mail."
            !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> "Digite um e-mail válido."
            else -> null
        }
        erroSenha = if (senha.length < 6) "A senha deve ter ao menos 6 caracteres." else null

        if (listOf(erroNome, erroCpf, erroSus, erroEmail, erroSenha).all { it == null }) {
            onEfetuarCadastro(nome.trim(), cpf, cartaoSus, email.trim(), senha)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .imePadding()
            .verticalScroll(rememberScrollState())
    ) {
        BannerLogo()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    CampoComRotulo(
                        rotulo = "Nome Completo:",
                        valor = nome,
                        onValorChange = { nome = it; erroNome = null },
                        placeholder = "Insira o seu nome completo",
                        erro = erroNome,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        )
                    )
                    CampoComRotulo(
                        rotulo = "CPF:",
                        valor = cpf,
                        onValorChange = { cpf = it.filter(Char::isDigit).take(11); erroCpf = null },
                        placeholder = "00000000000",
                        erro = erroCpf,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                    CampoComRotulo(
                        rotulo = "Cartão SUS:",
                        valor = cartaoSus,
                        onValorChange = { cartaoSus = it.filter(Char::isDigit).take(15); erroSus = null },
                        placeholder = "000000000000000",
                        erro = erroSus,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        )
                    )
                    CampoComRotulo(
                        rotulo = "Email:",
                        valor = email,
                        onValorChange = { email = it; erroEmail = null },
                        placeholder = "exemplo@gmail.com",
                        erro = erroEmail,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        )
                    )
                    CampoComRotulo(
                        rotulo = "Senha:",
                        valor = senha,
                        onValorChange = { senha = it; erroSenha = null },
                        placeholder = "Insira a sua senha",
                        erro = erroSenha,
                        ocultarTexto = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        )
                    )
                }
            }

            BotaoAzul(texto = "Efetuar Cadastro", onClick = { efetuarCadastro() })
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun CadastroScreenPreview() {
    VezCertaTheme {
        CadastroScreen(onEfetuarCadastro = { _, _, _, _, _ -> })
    }
}