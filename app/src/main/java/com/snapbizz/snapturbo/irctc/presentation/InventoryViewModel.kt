package com.snapbizz.snapturbo.irctc.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snapbizz.snapturbo.irctc.domain.repository.InventoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InventoryViewModel @Inject constructor(
    private val repository: InventoryRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        InventoryUiState(isLoading = true)
    )

    val uiState = _uiState.asStateFlow()

    init {
        loadMenu()
    }

    private fun loadMenu() {
        viewModelScope.launch {

            try {

                val items = repository.getMenu()

                _uiState.value =
                    InventoryUiState(
                        items = items
                    )

            } catch (e: Exception) {

                _uiState.value =
                    InventoryUiState(
                        error = e.message
                    )
            }
        }
    }
}
