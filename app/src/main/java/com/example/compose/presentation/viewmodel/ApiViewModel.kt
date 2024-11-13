package com.example.compose.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.domain.repositary.ApiRepository
import com.example.compose.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository
) : ViewModel() {

    private val _loadingApi = MutableSharedFlow<Boolean>()
    val loadingApi = _loadingApi.asSharedFlow()


     fun getListing() {
        viewModelScope.launch {
            apiRepository.getListings().collect { result ->
                when (result) {
                    is Resources.Success -> {
                        result.data?.let { listings ->
                            Log.e("Api Logs", listings)
                        }
                    }

                    is Resources.Error -> {
                        Log.e("Api Logs", result.message.toString())
                    }

                    is Resources.Loading -> {
                        _loadingApi.emit(result.isLoading)
                        Log.e("Api Logs", "Loading status ... ${result.isLoading}")
                    }

                }

            }
        }
    }

}