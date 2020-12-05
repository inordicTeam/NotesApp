package com.example.note.ui

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.res.ColorStateList
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
//            ObjectAnimator.ofArgb(
//                btnAddNewNote,
//                "",
//                resources.getColor(android.R.color.white),
//                resources.getColor(R.color.colorAccent),
//                resources.getColor(android.R.color.white)
//            ).apply {
//                addUpdateListener {
//                    val animatedValue = it.animatedValue as Int
//                    btnAddNewNote.imageTintList = ColorStateList.valueOf(animatedValue)
//                }
//                duration = 600
//                repeatMode = ObjectAnimator.RESTART
//                repeatCount = ObjectAnimator.INFINITE
//                start()
//            }
//            (AnimatorInflater.loadAnimator(requireContext(), R.animator.simple_animation) as AnimatorSet).apply {
//                setTarget(btnAddNewNote)
//                start()
//            }
        }

        launch {
            val notes = async(Dispatchers.IO) { App.db.noteDao().getAll() }
            adapter.updateNotes(notes.await().map { it.note })
        }

    }
}