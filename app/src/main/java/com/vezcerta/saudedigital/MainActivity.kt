package com.vezcerta.saudedigital

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Assignment
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.vezcerta.saudedigital.components.BottomNavigationBar
import com.vezcerta.saudedigital.components.MainTopAppBar
import com.vezcerta.saudedigital.screens.fila.FilaScreen
import com.vezcerta.saudedigital.screens.login.LoginScreen
import com.vezcerta.saudedigital.screens.perfil.PerfilScreen
import com.vezcerta.saudedigital.screens.unidades.UnidadesScreen
import com.vezcerta.saudedigital.ui.theme.VezCertaTheme

val ThemeBlue = Color(0xFF0052A5)

enum class NavigationTab(
    val label: String, val icon: ImageVector
) {
    UNIDADES("Unidades", Icons.Default.LocationOn),
    FILA("Fila", Icons.AutoMirrored.Filled.Assignment),
    PERFIL("Perfil", Icons.Default.PermIdentity)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            VezCertaTheme {
                var logado by rememberSaveable { mutableStateOf(false) }

                if (!logado) {
                    LoginScreen(
                        onEntrar = { email, senha ->
                            // TODO: validar com backend
                            logado = true
                        },
                        onCadastrar = {
                            // TODO: tela de cadastro
                        },
                        onEsqueceuSenha = {
                            // TODO: recuperação de senha
                        }
                    )
                } else {
                    var selectedTab by rememberSaveable { mutableStateOf(NavigationTab.UNIDADES) }

                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = { MainTopAppBar() },
                        bottomBar = {
                            BottomNavigationBar(
                                selectedTab = selectedTab,
                                onTabSelected = { newTab -> selectedTab = newTab },
                            )
                        }
                    ) { innerPadding ->
                        val contentModifier = Modifier.padding(innerPadding)
                        when (selectedTab) {
                            NavigationTab.FILA -> FilaScreen(modifier = contentModifier)
                            NavigationTab.UNIDADES -> UnidadesScreen(modifier = contentModifier)
                            NavigationTab.PERFIL -> PerfilScreen(modifier = contentModifier)
                        }
                    }
                }
            }
        }
    }
}