package com.example.citygrid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citygrid.ui.theme.CityGridTheme

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityGridTheme {
                PantallaDashboard()
            }
        }
    }
}

@Composable
fun PantallaDashboard() {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White, // Fondo blanco para la sección superior
        bottomBar = { BarraNavegacionInferiorCompartida(currentTab = "Inicio") }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
        ) {
            // Sección Superior: Fondo Blanco
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                BloqueEncabezado()
                
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Saludo de Bienvenida
                    Column {
                        Text(
                            text = "Bienvenido de vuelta,",
                            color = Color(0xFF718096),
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Hola, Administrador",
                            color = Color(0xFF1A202C),
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Text(
                            text = "Dashboard General · CityGrid",
                            color = Color(0xFF00A8CC),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    // Tarjeta Teal Gradient: Estado General
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(Color(0xFF1E3A47), Color(0xFF007A99))
                                    )
                                )
                                .padding(20.dp)
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Text(
                                    text = "Estado General del Sistema",
                                    color = Color(0xFFCBD5E0),
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = "07 / 06 / 2026 · 21:35 hrs",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                                
                                // Badge Alertas
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .background(Color.White.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(6.dp)
                                            .background(Color(0xFF00E5FF), CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = "2 Alertas pendientes de revisión",
                                        color = Color(0xFF00E5FF),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                }
                                
                                Text(
                                    text = "Última actualización hace 5 minutos",
                                    color = Color(0xFFCBD5E0),
                                    fontSize = 11.sp
                                )
                            }
                        }
                    }

                    // Botones de Acceso Rápido (Residuos, Agua, Alumbrado)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        BotonAccesoRapido(
                            iconName = "trash",
                            label = "Residuos",
                            iconColor = Color(0xFF00A8CC),
                            backgroundColor = Color(0xFFE6F8FA),
                            onClick = {
                                navegarAActividad(context, ResiduosActivity::class.java)
                            },
                            modifier = Modifier.weight(1f)
                        )
                        BotonAccesoRapido(
                            iconName = "water",
                            label = "Agua",
                            iconColor = Color(0xFF00A8CC),
                            backgroundColor = Color(0xFFE6F8FA),
                            onClick = {
                                Toast.makeText(context, "Módulo Agua no disponible", Toast.LENGTH_SHORT).show()
                            },
                            modifier = Modifier.weight(1f)
                        )
                        BotonAccesoRapido(
                            iconName = "sun",
                            label = "Alumbrado",
                            iconColor = Color(0xFFD69E2E),
                            backgroundColor = Color(0xFFFFFAF0),
                            onClick = {
                                navegarAActividad(context, AlumbradoActivity::class.java)
                            },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Sección Inferior: Fondo Negro
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    //.background(Color(0xFF0B0F12))
                    .background(Color.White)
                    .padding(horizontal = 16.dp, vertical = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Card Nivel de Residuos
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(20.dp)),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(
                        modifier = Modifier.padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Nivel de Residuos",
                            color = Color(0xFF1E293B),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            DibujoMedidorSegmentadoTeal()
                            Column(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(bottom = 4.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "90%",
                                    color = Color(0xFF1E293B),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 28.sp
                                )
                                Text(
                                    text = "Contenedor Plástico",
                                    color = Color(0xFF64748B),
                                    fontSize = 11.sp
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Estado Alert
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFFEF2F2), RoundedCornerShape(12.dp))
                                .padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = "Estado",
                                color = Color(0xFF64748B),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Text(
                                text = "⚠ Lleno — Requiere vaciado",
                                color = Color(0xFFEF4444),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                    }
                }

                Text(
                    text = "Sistemas monitoreados",
                    color = Color(0xFF475569),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )

                // Twin Grid Cards: Agua & Alumbrado
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Tarjeta Agua
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color(0xFFE6F8FA), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconoPersonalizado(name = "water", tint = Color(0xFF00A8CC), modifier = Modifier.size(18.dp))
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFE6F7F0), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(text = "NORMAL", color = Color(0xFF00A896), fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Agua", color = Color(0xFF64748B), fontSize = 11.sp)
                            Text(
                                text = buildAnnotatedString {
                                    withStyle(style = SpanStyle(color = Color(0xFF1E293B), fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                                        append("78")
                                    }
                                    withStyle(style = SpanStyle(color = Color(0xFF64748B), fontSize = 12.sp, fontWeight = FontWeight.Bold)) {
                                        append("%")
                                    }
                                }
                            )
                            Text(text = "Nivel del tanque", color = Color(0xFF94A3B8), fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                            BarraProgresoPersonalizada(progress = 0.78f, color = Color(0xFF00A896))
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Bomba", color = Color(0xFF94A3B8), fontSize = 10.sp)
                                Text(text = "Apagada", color = Color(0xFF00A896), fontWeight = FontWeight.Bold, fontSize = 10.sp)
                            }
                        }
                    }

                    // Tarjeta Alumbrado
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(16.dp)),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color(0xFFFFFAF0), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconoPersonalizado(name = "sun", tint = Color(0xFFD69E2E), modifier = Modifier.size(18.dp))
                                }
                                Box(
                                    modifier = Modifier
                                        .background(Color(0xFFE6F8FA), RoundedCornerShape(8.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(text = "AUTO", color = Color(0xFF00A8CC), fontWeight = FontWeight.Bold, fontSize = 9.sp)
                                }
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Alumbrado", color = Color(0xFF64748B), fontSize = 11.sp)
                            Text(text = "ON", color = Color(0xFFD69E2E), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            Text(text = "Modo automático", color = Color(0xFF94A3B8), fontSize = 10.sp)
                            Spacer(modifier = Modifier.height(22.dp)) // Espaciado para alinear alturas
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(text = "Sensor LDR", color = Color(0xFF94A3B8), fontSize = 10.sp)
                                Text(text = "120 lux", color = Color(0xFFD69E2E), fontWeight = FontWeight.Bold, fontSize = 10.sp)
                            }
                        }
                    }
                }

                // Card Alertas Activas
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .background(Color(0xFFFEF2F2), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Outlined.Warning,
                                        contentDescription = null,
                                        tint = Color(0xFFEF4444),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(text = "Alertas activas", color = Color(0xFF64748B), fontSize = 13.sp)
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFFEF2F2), RoundedCornerShape(8.dp))
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text(text = "2 activas", color = Color(0xFFEF4444), fontWeight = FontWeight.Bold, fontSize = 10.sp)
                            }
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "2",
                                color = Color(0xFFEF4444),
                                fontWeight = FontWeight.Bold,
                                fontSize = 32.sp
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(text = "Requieren atención inmediata", color = Color(0xFF94A3B8), fontSize = 11.sp)
                                Row {
                                    Text(text = "1 Crítica", color = Color(0xFFEF4444), fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                    Text(text = " · ", color = Color(0xFF94A3B8), fontSize = 11.sp)
                                    Text(text = "1 Advertencia", color = Color(0xFFF59E0B), fontWeight = FontWeight.Bold, fontSize = 11.sp)
                                }
                            }
                        }
                    }
                }

                // Alertas Recientes Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Alertas recientes",
                        color = Color(0xFF475569),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Historial ›",
                        color = Color(0xFF00A8CC),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            Toast.makeText(context, "Historial no disponible", Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                // Tres Cards de Alertas
                AlertaFilaCard(
                    indicatorColor = Color(0xFFEF4444),
                    iconName = "trash",
                    iconColor = Color(0xFFEF4444),
                    iconBgColor = Color(0xFFFEF2F2),
                    title = "Contenedor Plástico al 90% — requiere vaciado",
                    badgeText = "CRÍTICO",
                    badgeColor = Color(0xFFEF4444),
                    timeText = "21:30 hrs"
                )

                AlertaFilaCard(
                    indicatorColor = Color(0xFFF59E0B),
                    iconName = "water",
                    iconColor = Color(0xFFF59E0B),
                    iconBgColor = Color(0xFFFEF3C7),
                    title = "Nivel de agua bajo — verificar suministro",
                    badgeText = "ADVERTENCIA",
                    badgeColor = Color(0xFFF59E0B),
                    timeText = "21:18 hrs"
                )

                AlertaFilaCard(
                    indicatorColor = Color(0xFF00A8CC),
                    iconName = "monitor",
                    iconColor = Color(0xFF00A8CC),
                    iconBgColor = Color(0xFFE6F8FA),
                    title = "ESP32 de residuos sin conexión",
                    badgeText = "INFORMACIÓN",
                    badgeColor = Color(0xFF00A8CC),
                    timeText = "20:55 hrs"
                )
            }
        }
    }
}

@Composable
fun BotonAccesoRapido(
    iconName: String,
    label: String,
    iconColor: Color,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(16.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .background(backgroundColor, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                IconoPersonalizado(name = iconName, tint = iconColor, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                color = Color(0xFF4A5568),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun DibujoMedidorSegmentadoTeal() {
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
            // 9 de 10 segmentos llenos, pintamos de teal
            val color = if (i < 9) Color(0xFF00A8CC) else Color(0xFFE2E8F0)
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
fun AlertaFilaCard(
    indicatorColor: Color,
    iconName: String,
    iconColor: Color,
    iconBgColor: Color,
    title: String,
    badgeText: String,
    badgeColor: Color,
    timeText: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(1.dp, Color(0xFF1E293B), RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Indicador de color lateral izquierdo
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(indicatorColor)
                    .align(Alignment.CenterVertically)
            )
            
            Row(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(iconBgColor, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconoPersonalizado(name = iconName, tint = iconColor, modifier = Modifier.size(18.dp))
                }
                Spacer(modifier = Modifier.width(12.dp))
                
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        color = Color(0xFF1E293B),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = badgeText,
                            color = badgeColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.sp
                        )
                        Text(
                            text = "  ·  $timeText",
                            color = Color(0xFF94A3B8),
                            fontSize = 9.sp
                        )
                    }
                }
                
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color(0xFF94A3B8),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

private fun navegarAActividad(context: Context, targetClass: Class<*>) {
    val intent = Intent(context, targetClass).apply {
        flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or Intent.FLAG_ACTIVITY_SINGLE_TOP
    }
    context.startActivity(intent)
}
