package com.example.vezcerta

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
import com.example.vezcerta.components.BottomNavigationBar
import com.example.vezcerta.components.MainTopAppBar
import com.example.vezcerta.screens.fila.FilaScreen
import com.example.vezcerta.screens.perfil.PerfilScreen
import com.example.vezcerta.screens.unidades.UnidadesScreen
import com.example.vezcerta.ui.theme.VezCertaTheme

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
                var selectedTab by rememberSaveable { mutableStateOf(NavigationTab.FILA) }
                Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                    MainTopAppBar()
                }, bottomBar = {
                    BottomNavigationBar(
                        selectedTab = selectedTab,
                        onTabSelected = { newTab -> selectedTab = newTab },
                    )
                }) { innerPadding ->
                    val contentModifier = Modifier.padding(innerPadding)
                    when (selectedTab) {
                        NavigationTab.FILA -> FilaScreen(
                            modifier = contentModifier
                        )

                        NavigationTab.UNIDADES -> UnidadesScreen(
                            modifier = contentModifier
                        )

                        NavigationTab.PERFIL -> PerfilScreen(
                            modifier = contentModifier
                        )
                    }
                }
            }
        }
    }
}