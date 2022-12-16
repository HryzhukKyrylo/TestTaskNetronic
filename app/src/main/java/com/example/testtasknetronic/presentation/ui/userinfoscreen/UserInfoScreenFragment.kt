package com.example.testtasknetronic.presentation.ui.userinfoscreen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testtasknetronic.R
import com.example.testtasknetronic.databinding.FragmentUserInfoScreenBinding
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.presentation.ui.base.BaseFragment
import com.example.testtasknetronic.utils.loadImage
import com.example.testtasknetronic.utils.showShortToast

class UserInfoScreenFragment : BaseFragment<FragmentUserInfoScreenBinding>() {
    private val args: UserInfoScreenFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (args.userModel != null) {
            showUserData(args.userModel!!)
        } else {
            requireContext().showShortToast(getString(R.string.user_info_screen_incorrect_data))
            findNavController().navigateUp()
        }
    }

    private fun showUserData(userModel: UserModel) {
        binding.ivUserPhoto.loadImage(userModel.pictureLarge)
        val name = StringBuilder()
            .append(userModel.firstName)
            .append(" ")
            .append(userModel.lastName)
        binding.tvUserName.text =
            stringFromResource(R.string.user_info_screen_user_name, name.toString())

        binding.tvUserCell.text =
            stringFromResource(R.string.user_info_screen_user_cell, userModel.cell)
        binding.tvUserEmail.text =
            stringFromResource(R.string.user_info_screen_user_email, userModel.email)
        binding.tvUserPhone.text =
            stringFromResource(R.string.user_info_screen_user_phone, userModel.phone)
        val test = StringBuilder()
            .append(userModel.country)
            .append(", ")
            .append(userModel.city)
        binding.tvUserCity.text =
            stringFromResource(R.string.user_info_screen_user_city, test.toString())
    }

    private fun stringFromResource(idResource: Int, textAppend: String): String {
        return String.format(
            resources.getString(idResource),
            textAppend
        )
    }
}