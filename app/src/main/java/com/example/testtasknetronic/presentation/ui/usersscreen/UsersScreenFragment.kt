package com.example.testtasknetronic.presentation.ui.usersscreen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasknetronic.databinding.FragmentUsersScreenBinding
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
    private val adapter = MainAdapter()

    private val viewModel: UsersViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
        initRecycler()
        initObservers()
    }

    private fun initRecycler() {
        recycler = binding.rvUsers
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