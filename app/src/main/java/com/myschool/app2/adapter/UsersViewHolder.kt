package com.myschool.app2.adapter

import androidx.recyclerview.widget.RecyclerView
import com.myschool.app2.databinding.UserItemBinding
import com.myschool.app2.model.User

class UsersViewHolder(private val binding: UserItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {

        binding.tvDate.text = user.birthday
        binding.tvName.text = user.name
        binding.tvUsername.text = user.username
        binding.tvAddress.text = user.address
        binding.tvInitial.text = user.name.first().toString()

    }
}