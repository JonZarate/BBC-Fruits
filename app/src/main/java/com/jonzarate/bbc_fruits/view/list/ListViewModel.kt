package com.jonzarate.bbc_fruits.view.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonzarate.bbc_fruits.arch.SingleLiveEvent
import com.jonzarate.bbc_fruits.data.model.Fruit
import com.jonzarate.bbc_fruits.data.repo.AppRepository
import com.jonzarate.bbc_fruits.data.repo.FruitRepository
import com.jonzarate.bbc_fruits.view.detail.DetailFragment
import kotlinx.coroutines.launch

class ListViewModel(
    private val repo: FruitRepository,
    private val app: AppRepository
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>(true)

    val fruits = MutableLiveData<ArrayList<Fruit>>()
    val clear = SingleLiveEvent<Nothing>()
    val launchDetailView = SingleLiveEvent<Fruit>()

    init {
        onRefresh()
    }

    fun onRefresh() {
        clear.call()
        isLoading.postValue(true)
        viewModelScope.launch {
            repo.getFruits()?.let {
                fruits.postValue(it.fruit)

            }
            isLoading.postValue(false)
        }
    }

    fun onFruitSelected(fruit: Fruit) {
        launchDetailView.postValue(fruit)
        app.pageRequested(DetailFragment::class.toString())
    }
}