package com.jonzarate.bbc_fruits.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonzarate.bbc_fruits.data.repo.AppRepository

class DetailViewModelFactory(
    private val app: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(app) as T
    }
}