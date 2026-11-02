package com.xtacked.launcher.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xtacked.launcher.AppContainer

class DetailsViewModelFactory(
    private val container: AppContainer,
    private val contentId: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(DetailsViewModel::class.java))
        return DetailsViewModel(
            contentId = contentId,
            contentRepository = container.contentRepository,
            myListRepository = container.myListRepository
        ) as T
    }
}
