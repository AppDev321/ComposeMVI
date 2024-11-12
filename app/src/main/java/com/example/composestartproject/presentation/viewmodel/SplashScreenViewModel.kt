package com.example.composestartproject.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.composestartproject.presentation.base.BaseViewModel
import com.example.composestartproject.presentation.reducer.SplashEffect
import com.example.composestartproject.presentation.reducer.SplashEvent
import com.example.composestartproject.presentation.reducer.SplashScreenReducer
import com.example.composestartproject.presentation.reducer.SplashState
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