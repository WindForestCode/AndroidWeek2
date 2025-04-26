package com.myschool.app2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.myschool.app2.R
import com.myschool.app2.database.AppDb
import com.myschool.app2.databinding.FragmentProfileBinding
import com.myschool.app2.model.User
import com.myschool.app2.repository.RoomUsersRepository
import com.myschool.app2.viewmodel.ProfileViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater)

        val viewModel by activityViewModels<ProfileViewModel> {
            viewModelFactory {
                initializer {
                    ProfileViewModel(RoomUsersRepository(AppDb.getInstance(requireContext().applicationContext).userDao))
                }
            }
        }

        binding.buttonRefresh.setOnClickListener {

            if (!viewModel.refreshUser()) {
                Toast.makeText(
                    context,
                    context?.getString(R.string.db_empty_message),
                    Toast.LENGTH_SHORT
                ).show()
            }


        }

        viewModel.uiState
            .flowWithLifecycle(lifecycle)
            .onEach {
                bindProfile(it.user)
            }.launchIn(lifecycleScope)
        return binding.root
    }

    private fun bindProfile(user: User?) {
        binding.tvName.text = user?.name
        binding.tvSex.text = user?.sex
        binding.tvEmail.text = user?.email
        binding.tvAddress.text = user?.address
        binding.tvDateBirth.text = user?.birthday
        binding.tvInitial.text = user?.name?.first().toString()
        binding.tvUsername.text = user?.username

    }

}