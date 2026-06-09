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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
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

class AguaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // Asume que tienes tu CityGridTheme definido en tu proyecto
            // CityGridTheme {
            PantallaAgua()
            // }
        }
    }
}

@Composable
fun PantallaAgua() {
    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF4F7F9),
        bottomBar = { BarraNavegacionInferiorCompartida(currentTab = "Agua") }
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
                SeccionBienvenidaAgua()
                BannerEstadoAgua()

                Text(
                    text = "NIVEL DEL TANQUE",
                    color = Color(0xFF718096),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                TarjetaNivelPrincipal()
                TarjetaNivelVisual()

                Text(
                    text = "SISTEMAS MONITOREADOS",
                    color = Color(0xFF718096),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                )

                TarjetaSistemasMonitoreados()
                TarjetaEventosRecientesAgua()

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Última actualización: 06/06/2026 · 11:20 AM",
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
fun SeccionBienvenidaAgua() {
    Column {
        Text(
            text = "Bienvenido de vuelta",
            color = Color(0xFF718096),
            fontSize = 13.sp
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color(0xFF00A8CC), fontWeight = FontWeight.Bold)) {
                    append("Gestión de Agua")
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
fun BannerEstadoAgua() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1F3540))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "07/06/2026 · 11:20 hrs",
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF00E5FF), CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Sistema hidráulico activo",
                    color = Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun TarjetaNivelPrincipal() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column {
                    Text(text = "72%", color = Color(0xFF00A8CC), fontWeight = FontWeight.Bold, fontSize = 32.sp)
                    Text(text = "Agua almacenada", color = Color(0xFF718096), fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFFE6F8FA), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconoPersonalizado(name = "water", tint = Color(0xFF00A8CC), modifier = Modifier.size(28.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            BarraProgresoPersonalizada(progress = 0.72f, color = Color(0xFF00A8CC))
            Spacer(modifier = Modifier.height(16.dp))

            // Fila de 3 etiquetas de estado
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EtiquetaEstadoAgua(titulo = "Estado bomba", valor = "Apagada", modifier = Modifier.weight(1f))
                EtiquetaEstadoAgua(titulo = "Estado general", valor = "Operando", colorValor = Color(0xFF00A896), modifier = Modifier.weight(1f))
                EtiquetaEstadoAgua(titulo = "Sensor", valor = "Ultrasónico", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun EtiquetaEstadoAgua(titulo: String, valor: String, colorValor: Color = Color(0xFF2D3748), modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFFF4F7F9), RoundedCornerShape(8.dp))
            .padding(10.dp)
    ) {
        Text(text = titulo, color = Color(0xFF718096), fontSize = 10.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = valor, color = colorValor, fontWeight = FontWeight.Bold, fontSize = 12.sp)
    }
}

@Composable
fun TarjetaNivelVisual() {
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
                text = "Nivel visual del tanque",
                color = Color(0xFF2D3748),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(110.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                DibujoMedidorAguaSegmentado()
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "72%", color = Color(0xFF2D3748), fontWeight = FontWeight.Bold, fontSize = 28.sp)
                    Text(text = "Nivel del tanque", color = Color(0xFF718096), fontSize = 11.sp)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "✓ Nivel adecuado",
                    color = Color(0xFF00A896),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun DibujoMedidorAguaSegmentado() {
    Canvas(
        modifier = Modifier
            .width(180.dp)
            .height(90.dp)
    ) {
        val w = size.width
        val h = size.height
        val numSegments = 5
        val startAngle = 180f
        val totalSweep = 180f
        val segmentSweep = (totalSweep / numSegments) * 0.8f
        val gapSweep = (totalSweep / numSegments) * 0.2f
        val strokeWidthPx = 14.dp.toPx()

        for (i in 0 until numSegments) {
            val segStart = startAngle + i * (segmentSweep + gapSweep)
            // Pintar 4 de 5 segmentos para simular el 72% (casi 80%)
            val color = if (i < 4) Color(0xFF00A8CC) else Color(0xFFE2E8F0)
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
fun TarjetaSistemasMonitoreados() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            FilaMetricaAgua(icon = "water", label = "Nivel del tanque", value = "72%", isHighlighted = true)
            HorizontalDivider(color = Color(0xFFEDF2F7))

            // Usamos un ícono por defecto de Android para la bomba (puedes ajustarlo si tienes uno propio)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconoPersonalizado(name = "monitor", tint = Color(0xFF00A8CC), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Bomba de agua", color = Color(0xFF718096), fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Apagada", color = Color(0xFF2D3748), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            HorizontalDivider(color = Color(0xFFEDF2F7))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF00A896),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = "Estado general", color = Color(0xFF718096), fontSize = 14.sp)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Operando correctamente", color = Color(0xFF00A896), fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            HorizontalDivider(color = Color(0xFFEDF2F7))
            FilaMetricaAgua(icon = "clock", label = "Última actualización", value = "06/06/2026 · 11:20", isHighlighted = false)
        }
    }
}

@Composable
fun FilaMetricaAgua(icon: String, label: String, value: String, isHighlighted: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconoPersonalizado(name = icon, tint = Color(0xFF00A8CC), modifier = Modifier.size(20.dp))
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
fun TarjetaEventosRecientesAgua() {
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

            ElementoEventoResiduos(
                title = "Nivel de agua actualizado: 72% — estado normal",
                subtitle = "NORMAL · 11:20 hrs",
                dotColor = Color(0xFF00A896) // Verde
            )
            HorizontalDivider(color = Color(0xFFEDF2F7), modifier = Modifier.padding(vertical = 10.dp))

            ElementoEventoResiduos(
                title = "Nivel de agua bajo — verificar suministro",
                subtitle = "ADVERTENCIA · 31 min",
                dotColor = Color(0xFFD69E2E) // Naranja/Amarillo
            )
            HorizontalDivider(color = Color(0xFFEDF2F7), modifier = Modifier.padding(vertical = 10.dp))

            ElementoEventoResiduos(
                title = "Bomba de agua apagada automáticamente",
                subtitle = "INFORMACIÓN · 09:45 hrs",
                dotColor = Color(0xFF00A8CC)
            )
        }
    }
}
@Composable
fun BarraProgresoPersonalizada(progress: Float, color: Color) {
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

@Composable
fun ElementoEventoResiduos(title: String, subtitle: String, dotColor: Color) {
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