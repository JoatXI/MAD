package com.example.sqlitedemo;

import androidx.room.*;

@Dao
interface BillboardDao {
    @Query("SELECT * FROM billboard WHERE id = :id")
    fun getSongById(id: Long.Companion): Songs?

    @Query("SELECT * FROM billboard")
    fun getAllSongs(): List<Songs>

    @Insert
    fun insert(song: Songs): Long

    @Update
    fun update(song: Songs): Int

    @Delete
    fun delete(song: Songs): Int
}
