package com.example.networkcomm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.networkcomm.ui.theme.NetworkCommTheme
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson // for JSON - uncomment when needed
import com.github.kittinunf.fuel.gson.responseObject // for GSON - uncomment when needed
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkCommTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddSong()
                }
            }
        }
    }
}

@Composable
fun ArtistSearch() {
    Column {
        var songs by remember { mutableStateOf("") }
        var searchRes by remember { mutableStateOf("") }

        Column {
            TextField(value = songs, onValueChange = {songs=it})

            Button(onClick = {
                val url = "http://10.0.2.2:3000/artist/$songs"
                url.httpGet().responseJson { request, response, result ->
                    when(result) {
                        is Result.Success -> {
                            val jsonArray = result.get().array()
                            var str = ""
                            for(i in 0 until jsonArray.length()) {
                                val currObj = jsonArray.getJSONObject(i)
                                str += "Song ID: ${currObj.getString("id")} Song Title: ${currObj.getString("price")} Artist: ${currObj.getString("artist")} Release Year ${currObj.getString("year")}\n"
                            }
                            searchRes = str
                        }
                        is Result.Failure -> {
                            searchRes = "Error ${result.error.message}"
                        }
                    }
                }
            }) {
                Text("Search")
            }
            Text(searchRes)
        }
    }
}

@Composable
fun AddSong() {
    Column {
        var title by remember { mutableStateOf("") }
        var artist by remember { mutableStateOf("") }
        var year by remember { mutableStateOf("") }
        var downloads by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("") }
        var quantity by remember { mutableStateOf("") }

        var outputText by remember { mutableStateOf("") }

        Column {
            TextField(value = title, onValueChange = {title=it})
            TextField(value = artist, onValueChange = {artist=it})
            TextField(value = year, onValueChange = {year=it})
            TextField(value = downloads, onValueChange = {downloads=it})
            TextField(value = price, onValueChange = {price=it})
            TextField(value = quantity, onValueChange = {quantity=it})

            Button(onClick = {
                val url = "http://10.0.2.2:3000/song/create"
                val postData = listOf("title" to title, "artist" to artist, "year" to year.toInt(), "downloads" to downloads.toInt(), "price" to price.toFloat(), "quantity" to quantity.toInt())
                url.httpPost(postData).response { request, response, result ->
                    when(result) {
                        is Result.Success -> {
                            outputText = result.get().decodeToString()
                        }
                        is Result.Failure -> {
                            outputText = "Error ${result.error.message}"
                        }
                    }
                }
            }) {
                Text("ADD SONG")
            }
            Text(outputText)
        }
    }
}