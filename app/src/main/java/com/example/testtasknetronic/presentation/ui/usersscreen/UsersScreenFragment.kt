package com.example.testtasknetronic.presentation.ui.usersscreen

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasknetronic.databinding.FragmentUsersScreenBinding
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.presentation.ui.adapter.MainAdapter
import com.example.testtasknetronic.presentation.ui.adapter.MainItemDecoration
import com.example.testtasknetronic.presentation.ui.base.BaseFragment
import com.example.testtasknetronic.utils.Status
import com.example.testtasknetronic.utils.dp
import com.example.testtasknetronic.utils.showLongToast
import com.example.testtasknetronic.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UsersScreenFragment : BaseFragment<FragmentUsersScreenBinding>() {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var menuItem: MenuItem

    private val viewModel: UsersViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // First clear current all the menu items
        menu.clear();

        // Add the new menu items
        inflater.inflate(com.example.testtasknetronic.R.menu.main_menu, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            com.example.testtasknetronic.R.id.historyScreenFragment -> {
//                requireContext().showShortToast("History")
//                return true
//            }
            com.example.testtasknetronic.R.id.action_changeTheme -> {
                requireContext().showShortToast("Theme")
                return true
            }
            else -> {}
        }
        return item.onNavDestinationSelected(findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        recycler = binding.rvUsers
        adapter = MainAdapter { item ->
            viewModel.clickUser(item)
        }
        recycler.adapter = adapter
        recycler.addItemDecoration(
            MainItemDecoration(verticalSpace = (8.dp).toInt(), horizontalSpace = (8.dp).toInt())
        )
    }

    private fun initObservers() {
        viewModel.userResponse.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.SUCCESS -> {
                    Log.e("testtest", "initObservers: res = ${resource.data}")
                    if (resource.data != null) {
                        showNoListData(false)
                        adapter.submitList(resource.data)
                        showProgressBar(false)
                    } else {
                        showNoListData(true)
                        showProgressBar(false)
                    }
                }
                Status.ERROR -> {
                    requireContext().showShortToast(resource.message)
                    showProgressBar(false)
                }
                Status.LOADING -> {
                    requireContext().showLongToast("Load data")
                    showProgressBar(true)
                }
            }
        }

        viewModel.userClicked.observe(viewLifecycleOwner) { item ->
            goToUserInfo(item)
        }
    }

    private fun goToUserInfo(item: UserModel?) {
        val action =
            UsersScreenFragmentDirections.actionUsersScreenFragmentToUserInfoScreenFragment(item)
        findNavController().navigate(action)
    }

    private fun showNoListData(isVisible: Boolean) {
        binding.tvNoData.isVisible = isVisible
        binding.rvUsers.isVisible = !isVisible
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }

    private fun initClickListeners() {
        binding.btnGetUsers.setOnClickListener {
            viewModel.getUsers()
        }
    }
}