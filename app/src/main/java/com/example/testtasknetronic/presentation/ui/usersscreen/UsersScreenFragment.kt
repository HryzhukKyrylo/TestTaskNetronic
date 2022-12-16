package com.example.testtasknetronic.presentation.ui.usersscreen

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasknetronic.R
import com.example.testtasknetronic.databinding.FragmentUsersScreenBinding
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.presentation.ui.adapter.MainAdapter
import com.example.testtasknetronic.presentation.ui.adapter.MainItemDecoration
import com.example.testtasknetronic.presentation.ui.base.BaseFragment
import com.example.testtasknetronic.utils.Status
import com.example.testtasknetronic.utils.dp
import com.example.testtasknetronic.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersScreenFragment : BaseFragment<FragmentUsersScreenBinding>() {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter

    private val viewModel: UsersViewModel by viewModels()
    private var nightMode: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_changeTheme -> {
                requireContext().showShortToast("Theme")
                viewModel.switchNightMode()
                return true
            }
            else -> {
                item.onNavDestinationSelected(findNavController())
                        || super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_changeTheme)
        item.title = if (nightMode == true) {
            getString(R.string.users_screen_day_theme)
        } else {
            getString(R.string.users_screen_night_theme)
        }
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
                    showProgressBar(true)
                }
            }
        }

        viewModel.userClicked.observe(viewLifecycleOwner) { item ->
            goToUserInfo(item)
        }

        viewModel.isNightMode.observe(viewLifecycleOwner) {
            updateOptionsMenu(it)
        }
    }

    private fun updateOptionsMenu(isOn: Boolean) {
        nightMode = isOn
        requireActivity().invalidateOptionsMenu()
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