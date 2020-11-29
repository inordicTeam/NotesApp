package com.example.note.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.example.note.App
import com.example.note.R
import com.example.note.database.AppDatabase
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class NotesFragment : Fragment(), CoroutineScope {

    override val coroutineContext = Dispatchers.Main
    private val adapter = NotesAdapter {
        launch(Dispatchers.IO) { App.db.noteDao().delete(it) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        notesList.adapter = adapter
        btnAddNewNote.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_addNoteFragment)
        }

        launch {
            val notes = async(Dispatchers.IO) { App.db.noteDao().getAll() }
            adapter.updateNotes(notes.await().map { it.note })
        }
    }
}