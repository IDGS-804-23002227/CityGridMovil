package com.example.citygrid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.citygrid.ui.theme.CityGridTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CityGridTheme {
                PantallaLogin()
            }
        }
    }
}

@Composable
fun PantallaLogin() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    var email by remember { mutableStateOf("admin@citygrid.com") }
    var password by remember { mutableStateOf("password123") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF7F7F4) // Fondo crema/hueso del mockup
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Logo de la app (que incluye la imagen y el texto en logosinfondo.png)
            Image(
                painter = painterResource(id = R.drawable.logosinfondo),
                contentDescription = "CityGrid Logo",
                modifier = Modifier
                    .size(240.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Título "Inicio de sesión"
            Text(
                text = "Inicio de sesión",
                color = Color(0xFF1E3A47), // Color azul/teal oscuro
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Subtítulo
            Text(
                text = "Ingresa tus credenciales para acceder al sistema",
                color = Color(0xFF718096),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Campo: Correo Electrónico
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Correo electrónico",
                    color = Color(0xFF1E3A47),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color(0xFFE2E8F0),
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedTextColor = Color(0xFF1E3A47),
                        unfocusedTextColor = Color(0xFF1E3A47)
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = null,
                            tint = Color(0xFF94A3B8)
                        )
                    },
                    singleLine = true
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo: Contraseña
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Contraseña",
                    color = Color(0xFF1E3A47),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color(0xFFE2E8F0),
                        unfocusedBorderColor = Color(0xFFE2E8F0),
                        focusedTextColor = Color(0xFF1E3A47),
                        unfocusedTextColor = Color(0xFF1E3A47)
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFF94A3B8)
                        )
                    },
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            IconoOjo(
                                visible = isPasswordVisible,
                                tint = Color(0xFF718096),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    },
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ¿Olvidaste tu contraseña?
                Text(
                    text = "¿Olvidaste tu contraseña?",
                    color = Color(0xFF00A8CC),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            // Acción simulada
                        }
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            // Botón: Iniciar Sesión con gradiente
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF1E3A47), Color(0xFF007A99))
                        ),
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable {
                        // Navegación directa al Dashboard
                        val intent = Intent(context, DashboardActivity::class.java)
                        context.startActivity(intent)
                        (context as? ComponentActivity)?.finish()
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Iniciar Sesión",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Divisor con "o"
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
                Text(
                    text = "o",
                    color = Color(0xFF94A3B8),
                    modifier = Modifier.padding(horizontal = 10.dp),
                    fontSize = 12.sp
                )
                HorizontalDivider(modifier = Modifier.weight(1f), color = Color(0xFFE2E8F0))
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Texto Pie de Página
            Text(
                text = "¿No tienes una cuenta? Contacta al administrador.",
                color = Color(0xFF718096),
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun IconoOjo(visible: Boolean, tint: Color, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(24.dp)) {
        val w = size.width
        val h = size.height
        // Dibuja el contorno del ojo
        val path = Path().apply {
            moveTo(w * 0.15f, h * 0.5f)
            quadraticTo(w * 0.5f, h * 0.15f, w * 0.85f, h * 0.5f)
            quadraticTo(w * 0.5f, h * 0.85f, w * 0.15f, h * 0.5f)
            close()
        }
        drawPath(path, color = tint, style = Stroke(width = 2.dp.toPx()))
        // Dibuja el iris/pupila
        drawCircle(color = tint, radius = w * 0.16f, center = Offset(w * 0.5f, h * 0.5f))
        
        // Si no es visible, dibuja una línea diagonal que tacha el ojo
        if (!visible) {
            drawLine(
                color = tint,
                start = Offset(w * 0.25f, h * 0.25f),
                end = Offset(w * 0.75f, h * 0.75f),
                strokeWidth = 2.dp.toPx()
            )
        }
    }
}
