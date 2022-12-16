package com.example.testtasknetronic.presentation.ui.historyscreen

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasknetronic.databinding.FragmentHistoryScreenBinding
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.presentation.ui.adapter.MainAdapter
import com.example.testtasknetronic.presentation.ui.adapter.MainItemDecoration
import com.example.testtasknetronic.presentation.ui.base.BaseFragment
import com.example.testtasknetronic.utils.Status
import com.example.testtasknetronic.utils.dp
import com.example.testtasknetronic.utils.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryScreenFragment : BaseFragment<FragmentHistoryScreenBinding>() {
    private val viewModel: HistoryViewModel by viewModels()
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: MainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        iniObserve()
    }

    private fun iniObserve() {
        viewModel.historyUsers.observe(viewLifecycleOwner) { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showProgressBar(true)
                }
                Status.SUCCESS -> {
                    if (resource.data != null) {
                        showData(true)
                        showProgressBar(false)
                        adapter.submitList(resource.data)
                    } else {
                        showData(false)
                    }
                }
                Status.ERROR -> {
                    showProgressBar(false)
                    requireContext().showShortToast(resource.message)
                }
            }
        }

        viewModel.userModelClicked.observe(viewLifecycleOwner) {
            goToUserInfo(it)
        }
    }

    private fun goToUserInfo(model: UserModel?) {
        val action =
            HistoryScreenFragmentDirections.actionHistoryScreenFragmentToUserInfoScreenFragment(
                model
            )
        findNavController().navigate(action)
    }

    private fun showData(isVisible: Boolean) {
        binding.rvHistoryUsers.isVisible = isVisible
        binding.tvNoDataHistory.isVisible = !isVisible
    }

    private fun showProgressBar(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }


    private fun initRecycler() {
        recycler = binding.rvHistoryUsers
        adapter = MainAdapter {
            viewModel.clickUser(it)
        }
        recycler.adapter = adapter
        recycler.addItemDecoration(
            MainItemDecoration(verticalSpace = (8.dp).toInt(), horizontalSpace = (8.dp).toInt())
        )
    }

}