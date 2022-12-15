package com.example.testtasknetronic.presetation.ui.usersscreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.testtasknetronic.databinding.FragmentUsersScreenBinding
import com.example.testtasknetronic.presetation.ui.base.BaseFragment

class UsersScreenFragment : BaseFragment<FragmentUsersScreenBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.btnGoToHistory.setOnClickListener {
            val action =
                UsersScreenFragmentDirections.actionUsersScreenFragmentToHistoryScreenFragment()
            findNavController().navigate(action)
        }
        binding.btnGoToInfo.setOnClickListener {
            val action =
                UsersScreenFragmentDirections.actionUsersScreenFragmentToUserInfoScreenFragment()
            findNavController().navigate(action)
        }
    }

}