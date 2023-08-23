package com.example.examplecleanarch.repositoty

import com.example.examplecleanarch.network.ApiService
import okhttp3.RequestBody
import javax.inject.Inject

class AppApiRepository @Inject constructor(var apiService: ApiService) {

    fun getMovies() = apiService.getApiMovies()
    fun postCall(url: String, body: RequestBody) = apiService.postCall(url, body)

//    fun postWithHeaderCall(token: String, url: String, body: RequestBody) =
//        apiService.postWithTokenCall(token = token.getToken1(), url, body)
//
//    fun getCall(url: String, token: String) = apiService.getCall(url, token = token.getToken1())
//    fun getCallWithoutToken(url: String) = apiService.getCallWithoutToken(url)
}