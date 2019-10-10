package com.jonzarate.bbc_fruits.view.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonzarate.bbc_fruits.data.repo.AppRepository
import com.jonzarate.bbc_fruits.data.repo.FruitRepository

class ListViewModelFactory(
    private val repo: FruitRepository,
    private val app: AppRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListViewModel(repo, app) as T
    }
}