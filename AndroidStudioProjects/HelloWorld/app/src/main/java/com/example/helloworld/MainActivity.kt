package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Converter(
                Modifier.border(BorderStroke(4.dp, Color.Red))
                    .padding(20.dp)
            )
        }
    }

    @Composable
    fun TwoTexts() {
        Column {
            Text(resources.getString(R.string.helloworld), fontSize = 30.sp, color = Color.Blue, fontFamily = FontFamily.Cursive)
            Text("This is Android development")
        }
    }

    @Composable
    fun WelcomeBox() {
        var currName by remember { mutableStateOf("") }
        Column {
            TextField(value = currName, onValueChange = {currName=it})
            Text("Welcome $currName")
        }
    }

    @Composable
    fun Converter(mod: Modifier) {
        var feetUnit by remember { mutableStateOf(0.0) }
        Column(mod) {
            TextField(value = "$feetUnit", onValueChange = {feetUnit=it.toDouble()})
            val metreUnit = feetUnit * 0.305
            Text("Measurement in metre is $metreUnit")
        }
    }
}