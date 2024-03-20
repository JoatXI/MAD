package com.example.sqlitedemo

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sqlitedemo.ui.theme.SQLiteDemoTheme
import androidx.room.*

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
fun InputComposable() {
    Column {
        var id by remember { mutableStateOf("") }
        var name by remember { mutableStateOf("") }
        var title by remember { mutableStateOf("") }
        var year by remember { mutableStateOf("") }
    }
}