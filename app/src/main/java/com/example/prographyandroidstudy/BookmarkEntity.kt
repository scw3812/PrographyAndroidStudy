package com.example.prographyandroidstudy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
data class BookmarkEntity (@PrimaryKey(autoGenerate = true) val id: Long, var isMark: Boolean, var name: String, var image: String)
