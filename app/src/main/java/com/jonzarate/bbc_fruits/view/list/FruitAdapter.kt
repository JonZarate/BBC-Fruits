package com.jonzarate.bbc_fruits.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonzarate.bbc_fruits.data.model.Fruit
import com.jonzarate.bbc_fruits.databinding.ItemFruitBinding

class FruitAdapter(
    private val listener: OnUserClickListener
) : RecyclerView.Adapter<FruitViewHolder>() {

    interface OnUserClickListener {
        fun onUserClick(fruit: Fruit)
    }

    var fruits: List<Fruit>? = null

    override fun getItemCount(): Int {
        return fruits?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFruitBinding.inflate(inflater, parent, false)
        return FruitViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        fruits?.get(position)?.let {
            holder.bind(it)
        }
    }
}