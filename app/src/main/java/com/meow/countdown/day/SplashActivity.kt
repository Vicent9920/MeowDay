package com.meow.countdown.day

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import android.content.Intent

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SplashScreen { 
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SplashScreen(onFinish: () -> Unit) {
    val pagerState = rememberPagerState()
    val pages = listOf(
        SplashPage(
            title = "Meow Meow - Countdown day",
            description = "Record your important days",
            buttonText = "Next step →"
        ),
        SplashPage(
            title = "Interesting card style",
            description = "Important days, clear at a glance",
            buttonText = "Next step →"
        ),
        SplashPage(
            title = "Date reminder",
            description = "Never miss important moments",
            buttonText = "Start"
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            SplashPageContent(pages[page])
        }

        Row(
            Modifier
                .padding(bottom = 32.dp)
                .height(50.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pages.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) {
                    Color(0xFFFF9999)
                } else {
                    Color(0xFFFFE5E5)
                }
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }

        Button(
            onClick = {
                if (pagerState.currentPage < pages.size - 1) {
                    // Move to next page
                } else {
                    onFinish()
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9999)
            ),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = pages[pagerState.currentPage].buttonText,
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

data class SplashPage(
    val title: String,
    val description: String,
    val buttonText: String
)

@Composable
fun SplashPageContent(page: SplashPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Here you would add the cat illustrations for each page
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = page.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A4A4A)
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = page.description,
            fontSize = 16.sp,
            color = Color(0xFF808080)
        )
    }
}