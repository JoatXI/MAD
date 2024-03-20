package com.example.sqlitedemo;

import androidx.room.*;

@Dao
interface BillboardDao {
    @Query("SELECT * FROM billboard WHERE id = :id")
    fun getSongById(id: Long): Songs?

    @Update
    fun update(song: Songs): Int

    @Delete
    fun delete(song: Songs) :Int
}
