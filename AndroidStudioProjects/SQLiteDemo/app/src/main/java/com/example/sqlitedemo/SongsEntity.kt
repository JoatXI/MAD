package com.example.sqlitedemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "billboard")

data class Songs (
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "artist_name") val name: String,
    @ColumnInfo(name = "song_title") val title: String,
    @ColumnInfo(name = "year_released") val year: Int
)