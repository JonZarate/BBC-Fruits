package com.jonzarate.bbc_fruits.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jonzarate.bbc_fruits.data.repo.AppRepository
import kotlinx.coroutines.launch

class DetailViewModel(
    private val app: AppRepository
) : ViewModel() {

   fun onPageLoaded() {
       viewModelScope.launch {
           app.pageLoaded(DetailFragment::class.toString())
       }
   }
}