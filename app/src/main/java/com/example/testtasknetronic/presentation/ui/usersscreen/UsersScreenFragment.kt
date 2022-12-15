package com.example.testtasknetronic.presentation.ui.usersscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testtasknetronic.databinding.FragmentUsersScreenBinding
import com.example.testtasknetronic.presentation.ui.base.BaseFragment

class UsersScreenFragment : BaseFragment<FragmentUsersScreenBinding>() {

    private val viewModel: UsersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initObservers()
    }

    private fun initObservers() {
        //todo implement observ list users
    }

    private fun initClickListeners() {
        binding.btnGetUsers.setOnClickListener {
           viewModel.getUsers()
        }
    }

}