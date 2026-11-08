package com.example.vezcerta.screens.login

import android.util.Patterns
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vezcerta.R
import com.example.vezcerta.ThemeBlue
import com.example.vezcerta.ui.theme.VezCertaTheme

@Composable
fun LoginScreen(
    onEntrar: (email: String, senha: String) -> Unit,
    onCadastrar: () -> Unit,
    modifier: Modifier = Modifier,
    onEsqueceuSenha: () -> Unit = {}
) {
    var email by rememberSaveable { mutableStateOf("") }
    var senha by rememberSaveable { mutableStateOf("") }
    var erroEmail by remember { mutableStateOf<String?>(null) }
    var erroSenha by remember { mutableStateOf<String?>(null) }

    fun entrar() {
        erroEmail = when {
            email.isBlank() -> "Informe seu e-mail."
            !Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches() -> "Digite um e-mail válido."
            else -> null
        }
        erroSenha = if (senha.isEmpty()) "Informe sua senha." else null

        if (erroEmail == null && erroSenha == null) {
            onEntrar(email.trim(), senha)
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Seja bem-vindo!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Acesse sua conta ou se cadastre\npara iniciar a acompanhar sua fila",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(24.dp))

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
                        rotulo = "Email",
                        valor = email,
                        onValorChange = { email = it; erroEmail = null },
                        placeholder = "Insira seu email",
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
                        placeholder = "Insira sua senha",
                        erro = erroSenha,
                        ocultarTexto = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = { entrar() })
                    )

                    Text(
                        text = "Esqueceu sua senha?",
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.clickable(onClick = onEsqueceuSenha)
                    )

                    BotaoAzul(texto = "Entrar", onClick = { entrar() })
                    BotaoAzul(texto = "Cadastrar", onClick = onCadastrar)
                }
            }
        }
    }
}

@Composable
fun BannerLogo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ThemeBlue)
            .statusBarsPadding()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon_topbar),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "Saúde Digital",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )
        }
    }
}

@Composable
fun CampoComRotulo(
    rotulo: String,
    valor: String,
    onValorChange: (String) -> Unit,
    placeholder: String,
    erro: String?,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    ocultarTexto: Boolean = false,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = rotulo,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        OutlinedTextField(
            value = valor,
            onValueChange = onValorChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder) },
            singleLine = true,
            isError = erro != null,
            supportingText = if (erro != null) {
                { Text(erro) }
            } else null,
            visualTransformation = if (ocultarTexto) PasswordVisualTransformation()
            else VisualTransformation.None,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun BotaoAzul(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ThemeBlue,
            contentColor = Color.White
        )
    ) {
        Text(text = texto, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun LoginScreenPreview() {
    VezCertaTheme {
        LoginScreen(onEntrar = { _, _ -> }, onCadastrar = {})
    }
}