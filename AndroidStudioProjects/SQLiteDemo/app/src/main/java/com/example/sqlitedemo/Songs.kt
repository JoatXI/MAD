package com.example.sqlitedemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Year

@Entity(tableName = "billboard")

data class Songs (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "artist_name") var name: String,
    @ColumnInfo(name = "song_title") val title: String,
    @ColumnInfo(name = "year_released") var year: Int
)