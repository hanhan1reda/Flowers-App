package com.example.flowerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.GenericShape
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
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowerListScreen()
        }
    }
}

@Composable
fun FlowerListScreen() {
    val flowers = listOf(
        Pair("Dahlia", R.drawable.flower1),
        Pair("Aster", R.drawable.flower2),
        Pair("Marigold", R.drawable.flower3),
        Pair("Sunflower", R.drawable.flower4),
        Pair("Rose", R.drawable.flower5),
        Pair("Tulip", R.drawable.flower6),
        Pair("Lavender", R.drawable.flower7),
        Pair("Orchid", R.drawable.flower8)
    )

    var flowerList by remember { mutableStateOf(flowers) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3E5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Beautiful Flowers",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6A1B9A),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.weight(1f)
        ) {
            items(flowerList.size) { index ->
                FlowerCard(flowerList[index].first, flowerList[index].second, index)
            }
        }

        Button(
            onClick = { flowerList = emptyList() },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFAB47BC)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Text(text = "Clear All", color = Color.White, fontSize = 18.sp)
        }
    }
}

@Composable
fun FlowerCard(name: String, imageRes: Int, index: Int) {
    val cardColors = listOf(Color(0xFFE1BEE7), Color(0xFFD1C4E9))

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clip(CloudShape()),  // Apply cloud-like shape
        colors = CardDefaults.cardColors(
            containerColor = cardColors[index % cardColors.size]
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = name,
                modifier = Modifier
                    .size(160.dp)
                    .clip(CloudShape())
            )

            Text(
                text = name,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF4A148C),
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

fun CloudShape() = GenericShape { size, _ ->
    moveTo(size.width * 0.5f, 0f)
    cubicTo(size.width * 0.9f, 0f, size.width, size.height * 0.2f, size.width, size.height * 0.5f)
    cubicTo(size.width, size.height * 0.9f, size.width * 0.8f, size.height, size.width * 0.5f, size.height)
    cubicTo(size.width * 0.1f, size.height, 0f, size.height * 0.8f, 0f, size.height * 0.5f)
    cubicTo(0f, size.height * 0.1f, size.width * 0.1f, 0f, size.width * 0.5f, 0f)
    close()
}

@Preview
@Composable
fun PreviewFlowerListScreen() {
    FlowerListScreen()
}
