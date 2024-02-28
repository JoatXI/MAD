package com.example.androidmapping

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        val map1 = MapView(this)
        map1.controller.setCenter(GeoPoint(50.9, -1.4))
        map1.controller.setZoom(14.0)
        map1.setClickable(true)
        map1.setTileSource(TileSourceFactory.MAPNIK)
        setContentView(map1)
    }
}