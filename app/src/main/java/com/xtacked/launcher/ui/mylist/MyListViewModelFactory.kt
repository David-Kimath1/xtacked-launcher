package com.xtacked.launcher.ui.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtacked.launcher.AppContainer

class MyListViewModelFactory(private val container: AppContainer) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(MyListViewModel::class.java))
        return MyListViewModel(container.myListRepository) as T
    }
}
