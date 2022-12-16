package com.example.testtasknetronic.presentation.ui.historyscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.testtasknetronic.databinding.FragmentHistoryScreenBinding
import com.example.testtasknetronic.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryScreenFragment : BaseFragment<FragmentHistoryScreenBinding>() {
    private val viewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // todo implement
    }

}