package com.example.prographyandroidstudy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity(
    @PrimaryKey val id: Int,
    var isMark: Boolean,
    var name: String,
    var image: String
)