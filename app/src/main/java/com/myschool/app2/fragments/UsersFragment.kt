package com.myschool.app2.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.recyclerview.widget.GridLayoutManager
import com.myschool.app2.R
import com.myschool.app2.adapter.UsersAdapter
import com.myschool.app2.api.UsersApi
import com.myschool.app2.database.AppDb
import com.myschool.app2.databinding.FragmentUsersBinding
import com.myschool.app2.itemdecoration.OffsetDecoration
import com.myschool.app2.model.User
import com.myschool.app2.repository.NetworkUsersRepository
import com.myschool.app2.repository.RoomUsersRepository
import com.myschool.app2.util.Callback
import com.myschool.app2.viewmodel.UserViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class UsersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentUsersBinding.inflate(inflater)
        val api = UsersApi.INSTANCE
        val networkRepository = NetworkUsersRepository(api)



       val viewModel by activityViewModels<UserViewModel> {
           viewModelFactory {
               initializer {
                   UserViewModel(RoomUsersRepository(AppDb.getInstance(requireContext().applicationContext).userDao)) }
           }
       }

        val adapter = UsersAdapter()
        binding.buttonAdd.setOnClickListener {
            networkRepository.getUser(object : Callback<User> {
                override fun onSuccess(data: User) {
                    viewModel.saveUser(data)
                }

                override fun onError(throwable: Throwable) {
                    Log.e("UsersFragment", "Error fetching user", throwable)
                }
            })
        }

        binding.rcView.layoutManager = GridLayoutManager(requireContext(), 1)
        binding.rcView.adapter = adapter
        binding.rcView.addItemDecoration(
            OffsetDecoration(resources.getDimensionPixelOffset(R.dimen.small_spacing))
        )

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach { adapter.submitList(it.user) }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }


}