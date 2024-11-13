package com.example.compose.retrofit.usecase

interface UseCaseListener {
    fun onPreExecute()
    fun onPostExecute()
}