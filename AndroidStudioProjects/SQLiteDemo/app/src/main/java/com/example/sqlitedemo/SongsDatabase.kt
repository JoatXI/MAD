package com.example.sqlitedemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Songs::class), version = 1, exportSchema = false)
public abstract class SongsDatabase: RoomDatabase() {
    abstract fun songsDao(): BillboardDao

    companion object {
        private var instance: SongsDatabase? = null

        fun getDatabase(ctx: Context) : SongsDatabase {
            var tempInstance = instance
            if(tempInstance == null) {
                tempInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    SongsDatabase::class.java,
                    "songsDatabase"
                ).build()
                instance = tempInstance
            }
            return tempInstance
        }
    }
}