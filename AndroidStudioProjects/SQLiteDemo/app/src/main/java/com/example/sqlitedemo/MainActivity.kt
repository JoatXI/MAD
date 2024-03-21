package com.example.sqlitedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.sqlitedemo.ui.theme.SQLiteDemoTheme

class MainActivity : ComponentActivity() {
    var db : SongsDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        db = SongsDatabase.getDatabase(application)

        super.onCreate(savedInstanceState)
        setContent {
            SQLiteDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@Composable
fun InputComposable(songDao: BillboardDao) {

    var id by remember { mutableStateOf("") }
    var songName by remember { mutableStateOf("") }
    var songTitle by remember { mutableStateOf("") }
    var songYear by remember { mutableStateOf("") }

    Column {
        TextField(value = id, onValueChange = {id=it}, modifier = Modifier
            .weight(1.0f)
            .zIndex(2.0f)
            .padding(end = 2.dp))
        TextField(value = songName, onValueChange = {songName=it}, modifier = Modifier
            .weight(1.0f)
            .zIndex(2.0f)
            .padding(end = 2.dp))
        TextField(value = songTitle, onValueChange = {songTitle=it}, modifier = Modifier
            .weight(1.0f)
            .zIndex(2.0f)
            .padding(end = 2.dp))
        TextField(value = songYear, onValueChange = {songYear=it}, modifier = Modifier
            .weight(1.0f)
            .zIndex(2.0f)
            .padding(end = 2.dp))
    }

    Row {
        Button(onClick = { val s = Songs(songName, songTitle, songYear) }) { Text("ADD SONG")}
        Button(onClick = { songDao.getSongById(id = Long) }) { Text("FIND SONG")}
        Button(onClick = { songDao.update() }) { Text("UPDATE SONG")}
        Button(onClick = { songDao.delete() }) { Text("DELETE SONG")}
    }
}