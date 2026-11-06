package com.vezcerta.saudedigital.screens.unidades

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InfoTable(
    items: List<Pair<String, String>>, modifier: Modifier = Modifier
) {
    val borderColor = Color(0xFFE5E7EB)

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            items.forEachIndexed { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = item.first,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFF1E293B),
                        modifier = Modifier
                            .width(100.dp)
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    )

                    VerticalDivider(
                        modifier = Modifier.fillMaxHeight(), thickness = 1.dp, color = borderColor
                    )

                    Text(
                        text = item.second,
                        fontSize = 14.sp,
                        color = Color(0xFF334155),
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    )
                }

                if (index < items.size - 1) {
                    HorizontalDivider(
                        thickness = 1.dp, color = borderColor
                    )
                }
            }
        }
    }
}
