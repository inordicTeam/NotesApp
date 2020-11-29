package com.example.note.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("select * from categories")
    fun getAll(): List<Category>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)
}