package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.ViewModel.TodoViewModel
import com.example.todoapp.adapters.TodoAdapter
import com.example.todoapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getTodo().observe(viewLifecycleOwner) { todolist ->

            binding.rcvAllToDo.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rcvAllToDo.adapter = TodoAdapter(requireContext(), todolist)
        }

        binding.btnAddToDo.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createToDoFragment)
        }

        return binding.root
    }



}