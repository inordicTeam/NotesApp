package com.example.note.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class, Category::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
}