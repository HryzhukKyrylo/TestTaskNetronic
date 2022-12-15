package com.example.testtasknetronic.presentation.ui.splashscreen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.testtasknetronic.R
import com.example.testtasknetronic.databinding.FragmentSplashScreenBinding
import com.example.testtasknetronic.presentation.ui.base.BaseFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLaunch()
        goToNextScreen()
    }

    private fun goToNextScreen() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            delay(DELAY_TIMEOUT)
            withContext(Dispatchers.Main) {
                nextScreen()
            }
        }
    }

    private fun nextScreen() {
        val action =
            SplashScreenFragmentDirections.actionSplashScreenFragmentToUsersScreenFragment()
        findNavController().navigate(action)
    }

    private fun showLaunch() {
        val media = "android.resource://${requireActivity().packageName}/${R.raw.launch_gif}"
        Glide.with(requireContext())
            .load(media)
            .into(binding.ivGif)
    }

    companion object {
        const val DELAY_TIMEOUT = 2000L
    }
}