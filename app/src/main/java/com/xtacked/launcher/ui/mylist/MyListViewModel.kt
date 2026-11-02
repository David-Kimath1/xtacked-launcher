package com.xtacked.launcher.ui.mylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.data.repository.MyListRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MyListViewModel(private val repository: MyListRepository) : ViewModel() {

    val items: StateFlow<List<ContentItem>> = repository.observeItems()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun remove(id: String) {
        viewModelScope.launch { repository.remove(id) }
    }

    fun clearAll() {
        viewModelScope.launch { repository.clearAll() }
    }
}
