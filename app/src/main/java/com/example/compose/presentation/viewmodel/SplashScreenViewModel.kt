package com.example.compose.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.compose.presentation.base.BaseViewModel
import com.example.compose.presentation.reducer.SplashEffect
import com.example.compose.presentation.reducer.SplashEvent
import com.example.compose.presentation.reducer.SplashScreenReducer
import com.example.compose.presentation.reducer.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SplashScreenViewModel @Inject constructor() :
    BaseViewModel<SplashState, SplashEvent, SplashEffect>(
        initialState = SplashState.initial(),
        reducer = SplashScreenReducer()
    ) {


    init {
        sendEventForEffect(SplashEvent.SplashLoading(true))
        viewModelScope.launch {
            delay(2000)
            sendEvent(SplashEvent.UpdateSplashData(data = "Hello from view model"))
            sendEffect(SplashEffect.ShowSnackBar(message = "Hello from view model"))
            delay(1000)
            sendEvent(SplashEvent.UpdateSplashData(data = "Hello Effects from view model"))
            sendEffect(SplashEffect.ShowSnackBar(message = "Hello Effects from view model"))

            delay(1000)
            sendEventForEffect(SplashEvent.SplashLoading(false))
            sendEvent(SplashEvent.UpdateSplashData(data =  "Now Ending"))
            sendEffect(SplashEffect.ShowSnackBar(message = "Now Ending"))

        }

    }



}