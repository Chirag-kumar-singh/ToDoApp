package com.example.todoapp.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.ViewModel.TodoViewModel
import com.example.todoapp.databinding.FragmentCreateToDoBinding
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.model.Todo
import java.util.Date


class CreateToDoFragment : Fragment() {


    lateinit var binding: FragmentCreateToDoBinding
    var priority: String = ("1")
    val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreateToDoBinding.inflate(layoutInflater, container, false)

        binding.btnSaveToDo.setOnClickListener {
            createTodo(it)
        }

        binding.pGreen.setImageResource(R.drawable.done)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.done)
            binding.pRed.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "2"
            binding.pRed.setImageResource(R.drawable.done)
            binding.pGreen.setImageResource(0)
            binding.pYellow.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "3"
            binding.pYellow.setImageResource(R.drawable.done)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        return binding.root
    }

    private fun createTodo(it: View?){
        var title = binding.editTitle.text.toString()
        var subtitle = binding.editSubTitle.text.toString()
        var description = binding.editDescription.toString()

        val d = Date();
        val todoDate: CharSequence = DateFormat.format("MMMM, d, yyyy", d.time)

        val data =
            Todo(null,
                title = title,
                subtitle = subtitle,
                notes =  description,
                date = todoDate.toString(),
                priority)

        viewModel.addTodo(data)

        Toast.makeText(requireContext(), "Todo created successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createToDoFragment_to_homeFragment)
    }
}