package com.example.testtasknetronic.presentation.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.testtasknetronic.domain.model.UserModel

class NumberDiffUtilCallback : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}
