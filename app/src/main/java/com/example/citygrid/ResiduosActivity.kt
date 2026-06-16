package com.example.citygrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citygrid.ui.theme.CityGridTheme

class ResiduosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityGridTheme {
                PantallaResiduos()
            }
        }
    }
}

@Composable
fun PantallaResiduos() {
    // Define la estructura principal y el contenido de la pantalla de residuos.
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF4F7F9),
        bottomBar = { BarraNavegacionInferiorCompartida(currentTab = "Residuos") }
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
                BannerEstadoResiduos()
                TarjetaMedidor()
                Text(
                    text = "CONTENEDORES MONITOREADOS",
                    color = Color(0xFF718096),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                TarjetaContenedor(icon = "plastic", name = "Plástico", status = "Lleno", capacity = 90)
                TarjetaContenedor(icon = "trash", name = "Inorgánico", status = "Medio", capacity = 62)
                TarjetaContenedor(icon = "leaf", name = "Orgánico", status = "Vacío", capacity = 45)
                TarjetaEventosRecientesResiduos()
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Última actualización: 06/06/2026 · 10:35 AM",
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
fun BannerEstadoResiduos() {
    // Muestra la tarjeta con la hora actual y el conteo de contenedores críticos.
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F3540))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "07/06/2026 - 10:35 hrs",
                    color = Color(0xFFCBD5E0),
                    fontSize = 13.sp
                )
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFFAF0), RoundedCornerShape(12.dp))
                        .border(1.dp, Color(0xFFDD6B20), RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "⚠ 1 contenedor crítico",
                        color = Color(0xFFD69E2E),
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
                    text = "Sistema de residuos activo",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun TarjetaMedidor() {
    // Muestra la tarjeta que aloja al medidor semi-circular de residuos.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Nivel de Residuos — Contenedor más lleno",
                color = Color(0xFF2D3748),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                DibujoMedidorSegmentado()
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "90%", color = Color(0xFF2D3748), fontWeight = FontWeight.Bold, fontSize = 28.sp)
                    Text(text = "Contenedor Plástico", color = Color(0xFF718096), fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "⚠ Lleno — Requiere vaciado",
                    color = Color(0xFFE53E3E),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun DibujoMedidorSegmentado() {
    // Dibuja el arco semi-circular segmentado para el nivel del contenedor.
    Canvas(
        modifier = Modifier
            .width(180.dp)
            .height(90.dp)
    ) {
        val w = size.width
        val h = size.height
        val numSegments = 10
        val startAngle = 180f
        val totalSweep = 180f
        val segmentSweep = (totalSweep / numSegments) * 0.8f
        val gapSweep = (totalSweep / numSegments) * 0.2f
        val strokeWidthPx = 14.dp.toPx()

        for (i in 0 until numSegments) {
            val segStart = startAngle + i * (segmentSweep + gapSweep)
            val color = if (i < 9) Color(0xFFC53030) else Color(0xFFE2E8F0)
            drawArc(
                color = color,
                startAngle = segStart,
                sweepAngle = segmentSweep,
                useCenter = false,
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round),
                size = size.copy(width = w - strokeWidthPx, height = (h * 2) - strokeWidthPx),
                topLeft = Offset(strokeWidthPx / 2f, strokeWidthPx / 2f)
            )
        }
    }
}

@Composable
fun TarjetaContenedor(icon: String, name: String, status: String, capacity: Int) {
    // Muestra la tarjeta de detalle de capacidad y progreso de un contenedor.
    val (statusColor, statusBg, progressColor) = obtenerColoresContenedor(status)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(statusBg.copy(alpha = 0.3f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconoPersonalizado(name = icon, tint = progressColor)
                }
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = name, color = Color(0xFF2D3748), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(statusBg, RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(text = status, color = statusColor, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color(0xFF2D3748), fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                        append("$capacity")
                    }
                    withStyle(style = SpanStyle(color = Color(0xFF718096), fontSize = 13.sp)) {
                        append(" % de capacidad")
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            BarraProgresoPersonalizada(progress = capacity / 100f, color = progressColor)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Última actualización: 06/06/2026 · 10:35 AM", color = Color(0xFFA0AEC0), fontSize = 10.sp)
        }
    }
}

@Composable
fun BarraProgresoPersonalizada(progress: Float, color: Color) {
    // Dibuja una barra de progreso horizontal con esquinas redondeadas.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)
            .background(Color(0xFFEDF2F7), RoundedCornerShape(percent = 50))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(progress)
                .fillMaxHeight()
                .background(color, RoundedCornerShape(percent = 50))
        )
    }
}

private fun obtenerColoresContenedor(status: String): Triple<Color, Color, Color> {
    // Retorna los colores de estado correspondientes al nivel de residuos.
    return when (status) {
        "Lleno" -> Triple(Color(0xFFC53030), Color(0xFFFFF5F5), Color(0xFFE53E3E))
        "Medio" -> Triple(Color(0xFFD69E2E), Color(0xFFFFFAF0), Color(0xFFECC94B))
        else -> Triple(Color(0xFF00A896), Color(0xFFE6F7F0), Color(0xFF00A896))
    }
}

@Composable
fun TarjetaEventosRecientesResiduos() {
    // Muestra el historial corto de eventos para los contenedores de residuos.
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
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
            ElementoEventoResiduos(title = "Contenedor Plástico al 90% — requiere vaciado", subtitle = "CRÍTICO · 11:30 hrs", dotColor = Color(0xFFE53E3E))
            HorizontalDivider(color = Color(0xFFEDF2F7), modifier = Modifier.padding(vertical = 10.dp))
            ElementoEventoResiduos(title = "Sensor Inorgánico actualizado correctamente", subtitle = "INFORMACIÓN · 44 min", dotColor = Color(0xFF00A8CC))
            HorizontalDivider(color = Color(0xFFEDF2F7), modifier = Modifier.padding(vertical = 10.dp))
            ElementoEventoResiduos(title = "Contenedor Orgánico sin alertas activas", subtitle = "NORMAL · 1:10 hrs", dotColor = Color(0xFF00A896))
        }
    }
}

@Composable
fun ElementoEventoResiduos(title: String, subtitle: String, dotColor: Color) {
    // Renderiza un solo evento con viñeta de color dentro de la lista.
    Row(verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(8.dp)
                .background(dotColor, CircleShape)
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
            Text(text = subtitle, color = Color(0xFF718096), fontSize = 10.sp)
        }
    }
}
