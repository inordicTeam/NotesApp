package com.example.note.ui

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.note.App
import com.example.note.R
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.coroutines.*


class AddNoteFragment : Fragment(), CoroutineScope {

    override val coroutineContext = Dispatchers.Main

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch {
            val categories = withContext(Dispatchers.IO) { App.db.categoryDao().getAll() }
            Log.i("Categories", categories.toString())

            inputCategoryNote.setAdapter(
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    categories.map { it.name }
                )
            )
        }

        inputCategoryNote.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) inputCategoryNote.showDropDown()
        }

        btnAddNote.setOnClickListener {
//            val title = inputTitleNote.text.toString()
//            val body = inputTextNote.text.toString()
//            val category = inputCategoryNote.text.toString()
//
//            if (title.isNotEmpty() && body.isNotEmpty() && category.isNotEmpty()) {
//                launch {
//                    withContext(Dispatchers.IO) { App.db.categoryDao().insert(Category(category)) }
//                    withContext(Dispatchers.IO) { App.db.noteDao().insert(Note(title, body, category)) }
//                    findNavController().popBackStack()
//                }
//            }

//            val animScaleX = ObjectAnimator.ofFloat(btnAddNote, "scaleX", 1f, 1.5f, 1f)
//
//            val animScaleY = ObjectAnimator.ofFloat(btnAddNote, "scaleY", 1f, 1.5f, 1f)
//
//            val scaleAnim = AnimatorSet().apply {
//                playTogether(animScaleX, animScaleY)
//            }
//
//            val animAlpha = ObjectAnimator.ofFloat(btnAddNote, "alpha", 1f, 0.2f, 1f)
//
//            AnimatorSet().apply {
//                playTogether(scaleAnim, animAlpha)
//                duration = 600
//                start()
//            }
//            AnimatorSet().apply {
//
//                playTogether(
//
//                    AnimatorSet().apply {
//
//                        playTogether(
//
//                            ObjectAnimator.ofFloat(btnAddNote, "scaleX", 1f, 1.5f, 1f),
//                            ObjectAnimator.ofFloat(btnAddNote, "scaleY", 1f, 1.5f, 1f)
//
//                        )
//
//                    },
//                    ObjectAnimator.ofFloat(btnAddNote, "alpha", 1f, 0.2f, 1f)
//
//                )
//
//                duration = 600
//                start()
//            }

//            ObjectAnimator.ofArgb(
//                btnAddNote,
//                "backgroundColor",
//                resources.getColor(R.color.colorPrimary),
//                resources.getColor(R.color.colorPrimaryVariant),
//                resources.getColor(R.color.colorPrimary)
//            ).apply {
//                duration = 1000
//                start()
//            }

        }
    }
}