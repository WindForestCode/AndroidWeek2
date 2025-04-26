package com.myschool.app2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.myschool.app2.databinding.UserItemBinding
import com.myschool.app2.model.User

class UsersAdapter() : ListAdapter<User, UsersViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = UsersViewHolder(binding)

        return viewHolder
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    fun getCurrentListItems(): List<User> {
        return currentList
    }
}