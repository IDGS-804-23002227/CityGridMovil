package com.example.citygrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class AlertasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Asume que tienes tu CityGridTheme definido en tu proyecto
            // CityGridTheme {
            PantallaAlertas()
            // }
        }
    }
}

@Composable
fun PantallaAlertas() {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF4F7F9),
        bottomBar = { BarraNavegacionInferiorCompartida(currentTab = "Alertas") }
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
                SeccionBienvenidaAlertas()
                BannerEstadoAlertas()
                TarjetaResumenAlertas()
                FilaFiltrosAlertas()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Alertas recientes",
                        color = Color(0xFF2D3748),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
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

                // Lista de Tarjetas de Alertas
                TarjetaAlerta(
                    severidad = "CRÍTICO",
                    hora = "10:30 AM",
                    titulo = "Contenedor Plástico al 90%",
                    descripcion = "Nivel crítico de llenado — requiere vaciado urgente.",
                    modulo = "Residuos",
                    estado = "PENDIENTE"
                )

                TarjetaAlerta(
                    severidad = "ADVERTENCIA",
                    hora = "10:18 AM",
                    titulo = "Nivel de agua bajo",
                    descripcion = "Verificar suministro — tanque al 28% de capacidad.",
                    modulo = "Agua",
                    estado = "PENDIENTE"
                )

                TarjetaAlerta(
                    severidad = "INFORMACIÓN",
                    hora = "09:55 AM",
                    titulo = "ESP32 sin conexión",
                    descripcion = "Dispositivo de residuos sin comunicación detectada.",
                    modulo = "Sistema",
                    estado = "ATENDIDA"
                )

                TarjetaAlerta(
                    severidad = "NORMAL",
                    hora = "06/06/2026 · 18:45 PM",
                    titulo = "Encendido automático nocturno",
                    descripcion = "Sensor LDR detectó condición de noche. 125 luminarias activas.",
                    modulo = "Alumbrado",
                    estado = "ATENDIDA"
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun SeccionBienvenidaAlertas() {
    Column {
        Text(
            text = "Bienvenido de vuelta",
            color = Color(0xFF718096),
            fontSize = 13.sp
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF00A8CC), fontWeight = FontWeight.Bold)) {
                    append("Alertas y Notificaciones")
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
fun BannerEstadoAlertas() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F3540))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "09/06/2026 · 11:30 hrs",
                    color = Color(0xFFCBD5E0),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(Color(0xFF1E3A47), RoundedCornerShape(12.dp))
                        .border(1.dp, Color(0xFF00A896), RoundedCornerShape(12.dp))
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF00E5FF), CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sistema general activo",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun TarjetaResumenAlertas() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "2",
                color = Color(0xFFE53E3E),
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 16.dp)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Alertas activas",
                    color = Color(0xFF2D3748),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = "Requieren atención inmediata",
                    color = Color(0xFF718096),
                    fontSize = 11.sp
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                EtiquetaNotificacion(texto = "1 CRÍTICA", backgroundColor = Color(0xFFFFE5E5), textColor = Color(0xFFC53030))
                Spacer(modifier = Modifier.height(6.dp))
                EtiquetaNotificacion(texto = "1 ADVERTENCIA", backgroundColor = Color(0xFFFEFCBF), textColor = Color(0xFFB7791F))
            }
        }
    }
}

@Composable
fun FilaFiltrosAlertas() {
    val scrollFiltros = rememberScrollState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollFiltros),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ChipFiltro(texto = "Todas", activo = true)
        ChipFiltro(texto = "Residuos", activo = false)
        ChipFiltro(texto = "Agua", activo = false)
        ChipFiltro(texto = "Alumbrado", activo = false)
        ChipFiltro(texto = "Sistema", activo = false)
    }
}

@Composable
fun ChipFiltro(texto: String, activo: Boolean) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (activo) Color(0xFF00A8CC) else Color.White)
            .border(
                width = 1.dp,
                color = if (activo) Color.Transparent else Color(0xFFCBD5E0),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { /* Acción del filtro */ }
            .padding(horizontal = 16.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = if (activo) Color.White else Color(0xFF00A8CC),
            fontSize = 13.sp,
            fontWeight = if (activo) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
fun TarjetaAlerta(
    severidad: String,
    hora: String,
    titulo: String,
    descripcion: String,
    modulo: String,
    estado: String
) {
    // Configuración de colores dinámica basada en la severidad y estado
    val (iconBg, badgeBg, badgeText) = when (severidad) {
        "CRÍTICO" -> Triple(Color(0xFFFFE5E5), Color(0xFFFFE5E5), Color(0xFFC53030))
        "ADVERTENCIA" -> Triple(Color(0xFFFEFCBF), Color(0xFFFEFCBF), Color(0xFFB7791F))
        "INFORMACIÓN" -> Triple(Color(0xFFEBF8FF), Color(0xFFEBF8FF), Color(0xFF2B6CB0))
        else -> Triple(Color(0xFFC6F6D5), Color(0xFFC6F6D5), Color(0xFF2F855A))
    }

    val (statusBg, statusText) = when (estado) {
        "PENDIENTE" -> Pair(Color(0xFFFFE5E5), Color(0xFFC53030))
        else -> Pair(Color(0xFFC6F6D5), Color(0xFF2F855A))
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            // Cuadro de color a la izquierda
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(iconBg, RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))

            // Contenido de la alerta
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    EtiquetaNotificacion(texto = severidad, backgroundColor = badgeBg, textColor = badgeText)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = hora, color = Color(0xFFA0AEC0), fontSize = 10.sp)
                }
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = titulo,
                    color = Color(0xFF2D3748),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = descripcion,
                    color = Color(0xFF718096),
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = modulo,
                        color = Color(0xFF00A8CC),
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    EtiquetaNotificacion(texto = estado, backgroundColor = statusBg, textColor = statusText)
                }
            }
        }
    }
}

@Composable
fun EtiquetaNotificacion(texto: String, backgroundColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp
        )
    }
}