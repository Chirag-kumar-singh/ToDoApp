package com.example.todoapp.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.R
import com.example.todoapp.ViewModel.TodoViewModel
import com.example.todoapp.databinding.FragmentCreateToDoBinding
import com.example.todoapp.model.Todo
import java.util.*

class CreateToDoFragment : Fragment() {

    lateinit var binding: FragmentCreateToDoBinding
    var priority: String = "1"
    val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateToDoBinding.inflate(layoutInflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Create"
            setDisplayHomeAsUpEnabled(true)
        }

        setHasOptionsMenu(true)

        binding.btnSaveToDo.setOnClickListener {
            createTodo()
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

    private fun createTodo() {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val description = binding.editDescription.text.toString()

        val d = Date()
        val todoDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = Todo(
            null,
            title = title,
            subtitle = subtitle,
            notes = description,
            date = todoDate.toString(),
            priority
        )

        viewModel.addTodo(data)

        Toast.makeText(requireContext(), "Todo created successfully", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_createToDoFragment_to_homeFragment)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
