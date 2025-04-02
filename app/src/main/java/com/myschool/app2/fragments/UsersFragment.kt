package com.myschool.app2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myschool.app2.R
import com.myschool.app2.api.UsersApi
import com.myschool.app2.databinding.FragmentUsersBinding
import com.myschool.app2.model.User
import com.myschool.app2.repository.NetworkUsersRepository
import com.myschool.app2.util.Callback

class UsersFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUsersBinding.inflate(inflater)
        val api = UsersApi.INSTANCE
        val repository = NetworkUsersRepository(api)

        binding.buttonAdd.setOnClickListener {
            repository.getUser(object : Callback<User> {
                override fun onSuccess(user: User) {

                    Log.d("UsersFragment", "User fetched: $user")
                }

                override fun onError(exception: Throwable) {

                    Log.e("UsersFragment", "Error fetching user", exception)
                }
            })
        }

        return binding.root
    }


}