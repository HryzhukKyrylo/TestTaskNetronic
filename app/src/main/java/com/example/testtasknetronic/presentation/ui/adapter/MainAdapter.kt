package com.example.testtasknetronic.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testtasknetronic.databinding.ItemUserBinding
import com.example.testtasknetronic.domain.model.UserModel
import com.example.testtasknetronic.utils.loadImage

class MainAdapter(val clickListener: (UserModel) -> Unit) :
    ListAdapter<UserModel, MainViewHolder>(NumberDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MainViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item = item, clickListener)
    }

}

class MainViewHolder(private val binding: ItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        item: UserModel,
        clickListener: (UserModel) -> Unit,
    ) {
        binding.ivUserPhoto.loadImage(item.pictureThumb)
        binding.tvUserName.text = item.firstName
        binding.root.setOnClickListener {
            clickListener(item)
        }
    }
}

