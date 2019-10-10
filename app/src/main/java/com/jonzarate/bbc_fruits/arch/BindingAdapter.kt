package com.jonzarate.bbc_fruits.arch

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jonzarate.bbc_fruits.R

@BindingAdapter("pounds")
fun setPoundsPrice(view: TextView, pence: Int) {
    val pounds = pence / 100f
    view.text = view.context.resources.getString(R.string.pounds, pounds)
}

@BindingAdapter("kg")
fun setKgWeight(view: TextView, grams: Int) {
    val kg = grams / 100f
    view.text = view.context.resources.getString(R.string.kg, kg)
}