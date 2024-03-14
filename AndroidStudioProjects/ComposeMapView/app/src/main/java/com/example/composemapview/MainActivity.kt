package com.example.composemapview

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.example.composemapview.ui.theme.ComposeMapViewTheme
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMapViewTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    var lat by remember { mutableStateOf("") }
                    var lng by remember { mutableStateOf("") }
                    var geoPoint by remember { mutableStateOf(GeoPoint(0.0, 0.0)) }

                    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                        val mapHeight = this.maxHeight - 80.dp // Subtracting the height of the controls row

                        MapComposable(geoPoint, mapHeight)

                        Row(modifier = Modifier.height(80.dp).align(Alignment.BottomCenter)) {
                            TextField(value = lat, onValueChange = {lat=it}, label = {Text("Latitude")}, modifier = Modifier.weight(1.0f).zIndex(2.0f))
                            TextField(value = lng, onValueChange = {lng=it}, label = {Text("Longitude")}, modifier = Modifier.weight(1.0f).zIndex(2.0f))
                            Button(onClick = { geoPoint = GeoPoint(lat.toDouble(), lng.toDouble()) }) {
                                Text("View")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MapComposable( geoPoint: GeoPoint, mapHeight: Dp) {
    Column(modifier = Modifier.height(mapHeight)) {
        AndroidView(factory = { ctx ->
            Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

            MapView(ctx).apply {
                setClickable(true)
                setMultiTouchControls(true)
                setTileSource(TileSourceFactory.MAPNIK)
                controller.setZoom(10.0)
            }
        },
            update = { view -> view.controller.setCenter(geoPoint) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMapViewTheme {
        // Greeting("Android")
    }
}