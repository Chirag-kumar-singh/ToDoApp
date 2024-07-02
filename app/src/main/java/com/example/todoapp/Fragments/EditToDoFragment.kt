package com.example.todoapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentEditToDoBinding


class EditToDoFragment : Fragment() {

    val todo by navArgs<EditToDoFragmentArgs>()
    lateinit var binding:FragmentEditToDoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditToDoBinding.inflate(layoutInflater, container, false)

//        Log.d("EditToDoFragment", "Title: ${todo.data.title}")
//        Log.d("EditToDoFragment", "Subtitle: ${todo.data.subtitle}")
//        Log.d("EditToDoFragment", "Notes: ${todo.data.notes}")


        binding.editTitle.setText(todo.data.title)
        binding.editSubTitle.setText(todo.data.subtitle)
        binding.editDescription.setText(todo.data.notes)

        // Inflate the layout for this fragment
        return binding.root
    }

}