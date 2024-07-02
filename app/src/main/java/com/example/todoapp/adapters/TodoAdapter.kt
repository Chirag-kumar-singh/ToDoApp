package com.example.todoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Fragments.HomeFragment
import com.example.todoapp.Fragments.HomeFragmentDirections
import com.example.todoapp.R
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.model.Todo

class TodoAdapter(val requireContext: Context,val todolist: List<Todo>)
    : RecyclerView.Adapter<TodoAdapter.todoViewHolder>() {
    class todoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): todoViewHolder {
        return todoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    override fun onBindViewHolder(holder: todoViewHolder, position: Int) {

        val data = todolist[position]
        holder.binding.todoTitle.text = todolist[position].title
        holder.binding.todoSubtitle.text = todolist[position].subtitle
        holder.binding.todoDate.text = todolist[position].date

        when(todolist[position].priority){
            "1"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.green_dot)
            }
            "2"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.yellow_dot)
            }
            "3"->{
                holder.binding.viewPriority.setBackgroundResource(R.drawable.red_dot)
            }
        }

        holder.binding.root.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToEditToDoFragment(data)
            Navigation.findNavController(it).navigate(action)
        }

    }

}