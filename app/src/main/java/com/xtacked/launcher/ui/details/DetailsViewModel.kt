package com.xtacked.launcher.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtacked.launcher.data.model.ContentItem
import com.xtacked.launcher.data.repository.ContentRepository
import com.xtacked.launcher.data.repository.MyListRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class DetailsUiState(
    val loading: Boolean = true,
    val content: ContentItem? = null,
    val isSaved: Boolean = false
)

class DetailsViewModel(
    private val contentId: String,
    private val contentRepository: ContentRepository,
    private val myListRepository: MyListRepository
) : ViewModel() {

    private val _state = MutableStateFlow(DetailsUiState())
    val state: StateFlow<DetailsUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val content = contentRepository.getContent(contentId)
            _state.update { it.copy(loading = false, content = content) }
        }
        viewModelScope.launch {
            myListRepository.observeIsSaved(contentId).collect { saved ->
                _state.update { it.copy(isSaved = saved) }
            }
        }
    }

    fun toggleMyList() {
        val content = _state.value.content ?: return
        viewModelScope.launch { myListRepository.toggle(content) }
    }
}
