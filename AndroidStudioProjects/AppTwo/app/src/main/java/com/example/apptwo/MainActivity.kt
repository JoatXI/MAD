package com.example.apptwo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.material3.Surface
import com.example.apptwo.ui.theme.AppTwoTheme
import androidx.compose.material3.Button

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTwoTheme {
                Surface(modifier = Modifier.fillMaxSize(), shape = MaterialTheme.shapes.large, shadowElevation = 1.dp) {
                    Column {
                        Converter()
                        ShoppingList()
                    }
                }
            }
        }
    }

    @Composable
    fun Converter() {
        var feetUnit by remember { mutableStateOf(0.0) }

        Column(modifier = Modifier.padding(top = 20.dp).fillMaxWidth()) {
            val metreUnit = feetUnit * 0.305

            Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                TextField(value = "$feetUnit", onValueChange = {feetUnit=it.toDouble()}, modifier = Modifier.weight(2.0f))
                Button(modifier = Modifier.padding(8.dp).weight(1.0f), onClick = { feetUnit=0.0 } ) { Text("Reset") }
            }
            Text("Measurement in metre is $metreUnit")
        }
    }

    @Composable
    fun ShoppingList() {
        var list by remember { mutableStateOf(listOf<String>()) }
        var currItem by remember { mutableStateOf("") }

        Column(modifier = Modifier.padding(top = 20.dp).fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
                TextField(value = "$currItem", onValueChange = { currItem = it }, modifier = Modifier.weight(2.0f))
                Button(modifier = Modifier.padding(8.dp).weight(1.0f), onClick = {
                    var tempList = list.toMutableList()
                    tempList.add(currItem)
                    list = tempList
                }) { Text("Add Item") }

            }
            list.forEach {
                Text(it)
            }
        }
    }
}