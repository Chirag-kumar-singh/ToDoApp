package com.example.todoapp.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoapp.R
import com.example.todoapp.ViewModel.TodoViewModel
import com.example.todoapp.databinding.FragmentEditToDoBinding
import com.example.todoapp.model.Todo
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class EditToDoFragment : Fragment() {

    val todo by navArgs<EditToDoFragmentArgs>()
    lateinit var binding: FragmentEditToDoBinding
    var priority: String = "1"
    val viewModel: TodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditToDoBinding.inflate(layoutInflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.apply {
            title = "Edit"
            setDisplayHomeAsUpEnabled(true)
        }

        setHasOptionsMenu(true)

        binding.editTitle.setText(todo.data.title)
        binding.editSubTitle.setText(todo.data.subtitle)
        binding.editDescription.setText(todo.data.notes)

        when (todo.data.priority) {
            "1" -> {
                priority = "1"
                binding.pGreen.setImageResource(R.drawable.done)
                binding.pRed.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "2" -> {
                priority = "2"
                binding.pRed.setImageResource(R.drawable.done)
                binding.pGreen.setImageResource(0)
                binding.pYellow.setImageResource(0)
            }
            "3" -> {
                priority = "3"
                binding.pYellow.setImageResource(R.drawable.done)
                binding.pRed.setImageResource(0)
                binding.pGreen.setImageResource(0)
            }
        }

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

        binding.btnEditToDo.setOnClickListener {
            updateTodo()
        }

        return binding.root
    }

    private fun updateTodo() {
        val title = binding.editTitle.text.toString()
        val subtitle = binding.editSubTitle.text.toString()
        val description = binding.editDescription.text.toString()

        val d = Date()
        val todoDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = Todo(
            todo.data.id,
            title = title,
            subtitle = subtitle,
            notes = description,
            date = todoDate.toString(),
            priority
        )

        viewModel.updateTodo(data)

        Toast.makeText(requireContext(), "Todo updated successfully", Toast.LENGTH_SHORT).show()

        findNavController().navigate(R.id.action_editToDoFragment_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            val bottomSheet: BottomSheetDialog = BottomSheetDialog(requireContext())
            bottomSheet.setContentView(R.layout.dialog_delete)

            val textviewYes = bottomSheet.findViewById<TextView>(R.id.dialog_yes)
            val textviewNo = bottomSheet.findViewById<TextView>(R.id.dialog_no)

            textviewYes?.setOnClickListener {
                viewModel.deleteTodo(todo.data.id!!)
                bottomSheet.dismiss()
                findNavController().navigate(R.id.action_editToDoFragment_to_homeFragment)
            }
            textviewNo?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        } else if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
