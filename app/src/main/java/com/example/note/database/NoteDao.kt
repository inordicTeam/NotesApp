package com.example.note.database

import androidx.room.*

@Dao
interface NoteDao {
    @Transaction
    @Query("select * from notes")
    suspend fun getAll(): List<NoteAndCategory>

    @Insert
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)
}