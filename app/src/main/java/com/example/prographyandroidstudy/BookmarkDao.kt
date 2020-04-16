package com.example.prographyandroidstudy

import androidx.room.Dao
import androidx.room.Query

@Dao
interface BookmarkDao : BaseDao<BookmarkEntity>{
    @Query("SELECT * FROM bookmark")
    fun getAll(): List<BookmarkEntity>

    @Query("SELECT * FROM bookmark WHERE id=:id")
    fun getCity(id:Int) : BookmarkEntity

    @Query("DELETE FROM bookmark")
    fun deleteAll()
}