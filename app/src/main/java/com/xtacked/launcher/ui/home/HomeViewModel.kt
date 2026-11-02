package com.xtacked.launcher.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtacked.launcher.data.model.HomeFeed
import com.xtacked.launcher.data.NetworkMonitor
import com.xtacked.launcher.data.repository.ContentRepository
import com.xtacked.launcher.data.repository.MyListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class HomeUiState(
    val loading: Boolean = true,
    val feed: HomeFeed? = null,
    val savedIds: Set<String> = emptySet(),
    val online: Boolean = true
)

class HomeViewModel(
    private val contentRepository: ContentRepository,
    private val myListRepository: MyListRepository,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state: StateFlow<HomeUiState> = _state.asStateFlow()

    init {
        loadFeed()
        viewModelScope.launch {
            myListRepository.observeIds().collect { ids ->
                _state.update { it.copy(savedIds = ids) }
            }
        }
        viewModelScope.launch {
            networkMonitor.isOnline.collect { online ->
                _state.update { it.copy(online = online) }
            }
        }
    }

    private fun loadFeed() {
        viewModelScope.launch {
            val feed = contentRepository.getHomeFeed()
            _state.update { it.copy(loading = false, feed = feed) }
        }
    }
}
