package com.example.prographyandroidstudy

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.prographyandroidstudy.main.trip.BookmarkDao
import com.example.prographyandroidstudy.main.trip.BookmarkEntity

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun BookmarkDao(): BookmarkDao

    companion object{
        private var INSTANCE : LocalDatabase? = null

        fun getInstance(context: Context) : LocalDatabase?{
            if(INSTANCE == null){
                synchronized(LocalDatabase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "local_db.db").build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}