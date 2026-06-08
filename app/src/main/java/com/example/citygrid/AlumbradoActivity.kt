package com.example.citygrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citygrid.ui.theme.CityGridTheme

class AlumbradoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityGridTheme {
                PantallaAlumbrado()
            }
        }
    }
}

@Composable
fun PantallaAlumbrado() {
    // Define la estructura principal y el contenido de la pantalla de alumbrado.
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF4F7F9),
        bottomBar = { BarraNavegacionInferiorCompartida(currentTab = "Alumbrado") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            BloqueEncabezado()
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                SeccionBienvenida()
                BannerEstado()
                CuadriculaEstado()
                TarjetaMetricas()
                TarjetaEventosRecientes()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Última actualización: 06/06/2026 · 18:45 PM",
                    color = Color(0xFF718096),
                    fontSize = 11.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun SeccionBienvenida() {
    // Muestra el saludo de bienvenida en la parte superior.
    Column {
        Text(
            text = "Bienvenido de vuelta",
            color = Color(0xFF718096),
            fontSize = 13.sp
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF00A8CC), fontWeight = FontWeight.Bold)) {
                    append("Alumbrado Inteligente")
                }
                withStyle(style = SpanStyle(color = Color(0xFF2D3748), fontWeight = FontWeight.Bold)) {
                    append(" · CityGrid")
                }
            },
            fontSize = 20.sp
        )
    }
}

@Composable
fun BannerEstado() {
    // Muestra la tarjeta con la hora actual y el estado operativo del sistema.
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F3540))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "07/06/2026 - 18:45 hrs",
                    color = Color(0xFFCBD5E0),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(Color(0xFFE6F7F0), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "✓ Operando",
                        color = Color(0xFF00A896),
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF00E5FF), CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sistema de alumbrado activo",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun CuadriculaEstado() {
    // Muestra las tarjetas gemelas del estado físico y condición nocturna.
    Column {
        Text(
            text = "ESTADO GENERAL DEL ALUMBRADO",
            color = Color(0xFF718096),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(0xFFE6F8FA), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconoPersonalizado(name = "lightbulb", tint = Color(0xFF00A8CC))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Estado", color = Color(0xFF718096), fontSize = 12.sp)
                    Text(text = "ON", color = Color(0xFF00A8CC), fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Text(text = "Encendido automático", color = Color(0xFFA0AEC0), fontSize = 10.sp)
                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .background(Color(0xFFF0F2F5), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        IconoPersonalizado(name = "moon", tint = Color(0xFF4A5568))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = "Condición", color = Color(0xFF718096), fontSize = 12.sp)
                    Text(text = "Noche", color = Color(0xFF2D3748), fontWeight = FontWeight.Bold, fontSize = 22.sp)
                    Text(text = "Detectada por LDR", color = Color(0xFFA0AEC0), fontSize = 10.sp)
                }
            }
        }
    }
}

@Composable
fun TarjetaMetricas() {
    // Agrupa la lista de métricas detalladas del sensor y modo.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            FilaMetrica(icon = "sun", label = "Sensor LDR", value = "120 Lux", isHighlighted = false)
            HorizontalDivider(color = Color(0xFFEDF2F7))
            FilaMetrica(icon = "monitor", label = "Luminarias activas", value = "125 luminarias", isHighlighted = false)
            HorizontalDivider(color = Color(0xFFEDF2F7))
            FilaMetrica(icon = "clock", label = "Última actualización", value = "06/06/2026 · 18:45", isHighlighted = false)
            HorizontalDivider(color = Color(0xFFEDF2F7))
            FilaMetrica(icon = "settings", label = "Modo", value = "AUTO", isHighlighted = true)
        }
    }
}

@Composable
fun FilaMetrica(icon: String, label: String, value: String, isHighlighted: Boolean) {
    // Renderiza una sola fila dentro de la tarjeta de métricas.
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon == "settings") {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = null,
                tint = Color(0xFF00A8CC),
                modifier = Modifier.size(20.dp)
            )
        } else {
            IconoPersonalizado(name = icon, tint = Color(0xFF00A8CC), modifier = Modifier.size(20.dp))
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = label, color = Color(0xFF718096), fontSize = 14.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = value,
            color = if (isHighlighted) Color(0xFF00A8CC) else Color(0xFF2D3748),
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun TarjetaEventosRecientes() {
    // Muestra el historial corto de eventos con un botón para ver todo.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Eventos recientes",
                    color = Color(0xFF2D3748),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Historial ›",
                    color = Color(0xFF00A8CC),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable { }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            ElementoEvento(title = "Encendido automático por detección de noche", subtitle = "INFORMACIÓN · 18:45 hrs")
            HorizontalDivider(color = Color(0xFFEDF2F7), modifier = Modifier.padding(vertical = 10.dp))
            ElementoEvento(title = "Sistema operando sin fallas · 125 luminarias activas", subtitle = "NORMAL · 18:30 hrs")
            HorizontalDivider(color = Color(0xFFEDF2F7), modifier = Modifier.padding(vertical = 10.dp))
            ElementoEvento(title = "Lectura LDR registrada: 120 Lux", subtitle = "INFORMACIÓN · 18:00 hrs")
        }
    }
}

@Composable
fun ElementoEvento(title: String, subtitle: String) {
    // Renderiza un solo evento dentro de la lista de eventos.
    Row(
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(8.dp)
                .background(Color(0xFF00A8CC), CircleShape)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = title,
                color = Color(0xFF2D3748),
                fontSize = 13.sp,
                lineHeight = 16.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                color = Color(0xFF718096),
                fontSize = 10.sp
            )
        }
    }
}
