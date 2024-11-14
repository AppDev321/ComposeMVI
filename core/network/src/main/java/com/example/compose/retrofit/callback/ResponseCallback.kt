package com.example.compose.retrofit.callback

import com.example.compose.retrofit.remote.BaseError

interface OptimizedResponseCallBack<T>{
    fun onApiSuccess(response: T)
    fun onApiError(error: BaseError)
}