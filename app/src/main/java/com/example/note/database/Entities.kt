package com.example.note.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "notes")
data class Note(
    val title: String,
    val body: String,
    val categoryName: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey val name: String
)

data class NoteAndCategory(
    @Embedded val note: Note,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "name"
    )
    val category: Category
)