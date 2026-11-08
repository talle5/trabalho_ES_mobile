package com.vezcerta.saudedigital.screens.unidades

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.vezcerta.saudedigital.NavigationTab
import com.vezcerta.saudedigital.R
import com.vezcerta.saudedigital.components.BottomNavigationBar
import com.vezcerta.saudedigital.components.MainTopAppBar
import com.vezcerta.saudedigital.ui.theme.VezCertaTheme

@Composable
fun UnidadesScreen(
    modifier: Modifier = Modifier,
) {
    val dadosLocalizacao = listOf(
        "Cidade" to "Quixadá-CE",
        "Rua e Nº" to "Rua Epitácio Pessoa, 1111",
        "Bairro" to "Centro",
    )

    val ubsLocation = LatLng(-4.968644558143433, -39.01317093062767)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(ubsLocation, 17f)
    }

    val pointLocation = rememberMarkerState(position = ubsLocation)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "UBS Centro",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F172A),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 20.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isTrafficEnabled = false),
                uiSettings = MapUiSettings(zoomControlsEnabled = false)
            ) {
                Marker(
                    state = pointLocation,
                    title = "UBS Centro"
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(top = 24.dp, bottom = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location),
                contentDescription = "Localização",
                tint = Color(0xFF0F172A),
                modifier = Modifier.size(24 .dp)
            )
            Text(
                text = "Localização",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F172A)
            )
        }

        InfoTable(
            items = dadosLocalizacao
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UnidadesScreenPreview() {
    VezCertaTheme {
        Scaffold(
            topBar = { MainTopAppBar() },
            bottomBar = {
                BottomNavigationBar(
                    selectedTab = NavigationTab.UNIDADES,
                    onTabSelected = {}
                )
            }
        ) { innerPadding ->
            UnidadesScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
