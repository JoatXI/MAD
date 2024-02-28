package com.example.composemapview

import android.os.Bundle
import android.preference.PreferenceManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.viewinterop.AndroidView
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
                    var geoPoint: GeoPoint by remember { mutableStateOf(GeoPoint(50.9, -1.4)) }
                    MapComposable(geoPoint)
                }
            }
        }
    }
}

@Composable
fun MapComposable(geoPoint : GeoPoint) {

    AndroidView(factory = { ctx ->
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        MapView(ctx).apply {
            setClickable(true)
            setMultiTouchControls(true)
            setTileSource(TileSourceFactory.MAPNIK)
            controller.setZoom(14.0)
        }
    },
        update = { view -> view.controller.setCenter(geoPoint)}
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeMapViewTheme {
        // Greeting("Android")
    }
}