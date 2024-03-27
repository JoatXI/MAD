package com.example.sqlitedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
                    InputComposable(db!!.songsDao())
                }
            }
        }
    }
}

@Composable
fun InputComposable(songDao: BillboardDao) {
    Column(modifier = Modifier.fillMaxSize()) {
        var id by remember { mutableStateOf("") }
        var artistName by remember { mutableStateOf("") }
        var songTitle by remember { mutableStateOf("") }
        var songYear by remember { mutableStateOf("") }

        var result by remember { mutableStateOf("") }
        var song: Songs by remember { mutableStateOf(Songs(name = "", title = "", year = 0)) }

        Column {
            TextField(value = id, onValueChange = {id=it})
            TextField(value = artistName, onValueChange = {artistName=it})
            TextField(value = songTitle, onValueChange = {songTitle=it})
            TextField(value = songYear, onValueChange = {songYear=it})
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                val newSong = Songs(name = artistName, title =  songTitle, year =  songYear.toInt())
                val id = songDao.insert(newSong)
                result = id.toString()
            }) { Text("ADD SONG")}
            Text(result)

            Button(onClick = { song = songDao.getSongById(id = id.toLong())!! }) { Text("FIND SONG")}
            //Text(song.toString())
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                val updSong = Songs(id = id.toLong(), name = artistName, title = songTitle, year = songYear.toInt())
                songDao.update(updSong)
            }) { Text("UPDATE SONG")}
            //Text("Song updated successfully!")

            Button(onClick = {
                val delSong = Songs(id = id.toLong(), name = artistName, title = songTitle, year = songYear.toInt())
                songDao.delete(delSong) }) { Text("DELETE SONG")}
            //Text("Song successfully deleted")
        }
    }
}