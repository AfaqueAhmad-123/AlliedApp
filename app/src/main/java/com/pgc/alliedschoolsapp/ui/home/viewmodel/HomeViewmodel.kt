package com.pgc.alliedschoolsapp.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.examplecleanarch.repositoty.AppApiRepository
import com.example.examplecleanarch.resource.Resource
import com.example.examplecleanarch.utils.CallApis
import com.google.gson.JsonElement
import com.pgc.alliedschoolsapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    var respository: AppApiRepository,
    var callApi: CallApis,
) : ViewModel() {

    val liveData = MutableLiveData<Resource<JsonElement>>()

    fun getMovies() = viewModelScope.launch {

        callApi.callFuction(respository.getMovies()) {

            liveData.postValue(it)

        }

    }

    /*fun postCall(token: String, params: JSONObject) = viewModelScope.launch {

        val body: RequestBody = JSONObject(params.toString()).toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        callApi.callFuction(
            respository.postWithHeaderCall(
                token = token,
                "Constants.Attendance_END_POINT",
                body
            ),
            Constants.Attendance_END_POINT
        ) {
            liveData.postValue(it)
        }
    }*/

    /*fun postLoginCall(params: JSONObject) = viewModelScope.launch {

        val body: RequestBody = JSONObject(params.toString()).toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
        callApi.callFuction(
            respository.postCall(Constants.POST_LOGIN, body),
            Constants.POST_LOGIN
        ) {
            liveData.postValue(it)
        }
    }*/

    /*fun getMcqs(token: String, url: String) = viewModelScope.launch {

        callApi.callFuction(
            respository.getCall(Constants.GetAllMcqChapterWise + url, token),
            apiName = Constants.GetAllMcqChapterWise
        ) {
            liveData.postValue(it)
        }
    }*/

    /*fun getForgotPassword(param: String) {

        callApi.callFuction(
            respository.getCallWithoutToken(Constants.FORGOT_PASSWORD_ENDPOINT+ param),
            apiName = Constants.FORGOT_PASSWORD_ENDPOINT
        ) {
            liveData.postValue(it)
        }
    }*/
}