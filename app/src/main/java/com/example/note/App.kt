package com.example.note

import android.app.Application
import androidx.room.Room
import com.example.note.database.AppDatabase

class App : Application() {

    companion object {
        lateinit var db: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "main"
        ).build()
    }
}