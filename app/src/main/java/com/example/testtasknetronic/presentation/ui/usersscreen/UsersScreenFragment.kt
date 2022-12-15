package com.example.testtasknetronic.presentation.ui.usersscreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.example.testtasknetronic.databinding.FragmentUsersScreenBinding
import com.example.testtasknetronic.presentation.ui.base.BaseFragment
import com.example.testtasknetronic.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersScreenFragment : BaseFragment<FragmentUsersScreenBinding>() {

    private val viewModel: UsersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initObservers()
    }

    private fun initObservers() {
        viewModel.userResponse.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    //todo show data
                    Log.e("testtest", "initObservers: res = ${resource.data}", )
                }
                Status.ERROR -> {
                    //todo show error
                    Log.e("testtest", "initObservers: error", )

                }
                Status.LOADING -> {
                    //todo show progress
                    Log.e("testtest", "initObservers: load", )

                }
            }
        }
    }

    private fun initClickListeners() {
        binding.btnGetUsers.setOnClickListener {
            viewModel.getUsers()
        }
    }

}