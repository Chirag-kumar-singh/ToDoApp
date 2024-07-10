package com.example.todoapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.graphics.toColor
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.todoapp.R
import com.example.todoapp.ViewModel.TodoViewModel
import com.example.todoapp.adapters.TodoAdapter
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.model.Todo


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: TodoViewModel by viewModels()

    var oldMyTodo = arrayListOf<Todo>()

    lateinit var adapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        //(activity as AppCompatActivity).supportActionBar?.title = "Home"

        setHasOptionsMenu(true)

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding.rcvAllToDo.layoutManager = staggeredGridLayoutManager

        viewModel.getTodo().observe(viewLifecycleOwner) { todolist ->

            oldMyTodo = todolist as ArrayList<Todo>

            binding.rcvAllToDo.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = TodoAdapter(requireContext(), todolist)
            binding.rcvAllToDo.adapter = adapter
        }

        binding.allTodo.setOnClickListener {
            viewModel.getTodo().observe(viewLifecycleOwner) { todolist ->

                oldMyTodo = todolist as ArrayList<Todo>

                binding.rcvAllToDo.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = TodoAdapter(requireContext(), todolist)
                binding.rcvAllToDo.adapter = adapter
            }
        }

        binding.filterHigh.setOnClickListener{
            viewModel.getHighTodo().observe(viewLifecycleOwner) { todolist ->

                oldMyTodo = todolist as ArrayList<Todo>

                 binding.rcvAllToDo.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = TodoAdapter(requireContext(), todolist)
                binding.rcvAllToDo.adapter = adapter
            }
        }

        binding.filterMedium.setOnClickListener {
            viewModel.getMediumTodo().observe(viewLifecycleOwner) { todolist ->

                oldMyTodo = todolist as ArrayList<Todo>

                binding.rcvAllToDo.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = TodoAdapter(requireContext(), todolist)
                binding.rcvAllToDo.adapter = adapter
            }
        }

        binding.filterLow.setOnClickListener {
            viewModel.getLowTodo().observe(viewLifecycleOwner) { todolist ->

                oldMyTodo = todolist as ArrayList<Todo>

                binding.rcvAllToDo.layoutManager = GridLayoutManager(requireContext(), 2)
                adapter = TodoAdapter(requireContext(), todolist)
                binding.rcvAllToDo.adapter = adapter
            }
        }

        binding.btnAddToDo.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createToDoFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)

        val item = menu.findItem(R.id.app_bar_search)
        val searchView = item.actionView as SearchView
        searchView.queryHint = "Enter Todo here ..."
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                NotesFiltering(newText)
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun NotesFiltering(p0: String?){

        val newFilteredList = arrayListOf<Todo>()
        for(i in oldMyTodo){
            if(i.title.contains(p0!!) || i.subtitle.contains(p0!!)){
                newFilteredList.add(i)
            }
        }
        adapter.filtering(newFilteredList)
    }

}