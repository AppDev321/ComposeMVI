package com.example.compose.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.compose.presentation.base.BaseViewModel
import com.example.compose.presentation.reducer.SplashEffect
import com.example.compose.presentation.reducer.SplashEvent
import com.example.compose.presentation.reducer.SplashScreenReducer
import com.example.compose.presentation.reducer.SplashState
import com.example.domain.mapper.MovieMapper
import com.example.domain.usecase.MovieUseCase
import com.example.response.APIResult

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val movieMapper: MovieMapper
) :
    BaseViewModel<SplashState, SplashEvent, SplashEffect>(
        initialState = SplashState.initial(),
        reducer = SplashScreenReducer()
    ) {


    init {
        collectMovieState()
//        sendEventForEffect(SplashEvent.SplashLoading(true))
//        viewModelScope.launch {
//            delay(2000)
//            sendEvent(SplashEvent.UpdateSplashData(data = "Hello from view model"))
//            sendEffect(SplashEffect.ShowSnackBar(message = "Hello from view model"))
//            delay(1000)
//            sendEvent(SplashEvent.UpdateSplashData(data = "Hello Effects from view model"))
//            sendEffect(SplashEffect.ShowSnackBar(message = "Hello Effects from view model"))
//
//            delay(1000)
//            sendEventForEffect(SplashEvent.SplashLoading(false))
//            sendEvent(SplashEvent.UpdateSplashData(data =  "Now Ending"))
//            sendEffect(SplashEffect.ShowSnackBar(message = "Now Ending"))
//
//        }

    }

    private fun collectMovieState() {
        viewModelScope.launch {
            movieUseCase.getMovies().collect { result ->
                when (result) {
                    is APIResult.Loading -> {
                        //I want to add
                        sendEventForEffect(SplashEvent.SplashLoading(isLoading = result.isLoading))

                    }

                    is APIResult.Success -> {
                        val list = movieMapper.mapFrom(result.data!!)
                        list?.let {
                            sendEvent(SplashEvent.UpdateSplashData(data = it.toString()))
                        }
                    }

                    is APIResult.Error -> {
                        sendEvent(
                            SplashEvent.UpdateSplashData(
                                data = result.message ?: "Unknow errorrr"
                            )
                        )

                    }
                }
            }
        }
    }


}