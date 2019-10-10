package com.jonzarate.bbc_fruits.view.list

import androidx.recyclerview.widget.RecyclerView
import com.jonzarate.bbc_fruits.data.model.Fruit
import com.jonzarate.bbc_fruits.databinding.ItemFruitBinding

class FruitViewHolder(
    private val binding: ItemFruitBinding,
    private val listener: FruitAdapter.OnUserClickListener
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.root.setOnClickListener( {
            binding.fruit?.let {
                listener.onUserClick(it)
            }
        })
    }

    fun bind (fruit: Fruit) {
        binding.fruit = fruit
    }
}