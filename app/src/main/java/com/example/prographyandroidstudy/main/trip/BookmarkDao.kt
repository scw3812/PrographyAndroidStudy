package com.example.prographyandroidstudy.main.trip

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.prographyandroidstudy.BaseDao

@Dao
interface BookmarkDao :
    BaseDao<BookmarkEntity> {
    @Query("SELECT * FROM bookmark")
    fun getAll(): List<BookmarkEntity>

    @Query("SELECT * FROM bookmark WHERE id=:id")
    fun getCity(id:Int) : BookmarkEntity

    @Query("DELETE FROM bookmark")
    fun deleteAll()
}