package com.example.examplecleanarch.utils

import android.util.Log
import com.example.examplecleanarch.resource.Resource
import com.google.gson.JsonElement
import com.pgc.alliedschoolsapp.utils.Constants
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CallApis @Inject constructor(var networkManager: NetworkManager) {
    fun callFuction(
        call: Call<JsonElement?>,
        callBack: (Resource<JsonElement>) -> Unit,
    ) {

        if (networkManager.isNetworkAvailable()) {

            callBack(Resource.loading())
            try {
                call.enqueue(object : Callback<JsonElement?> {
                    override fun onResponse(
                        call: Call<JsonElement?>,
                        response: Response<JsonElement?>
                    ) {
                        if (response.isSuccessful && response.body() != null && response.code() == 200) {
                            try {

                                val message = ""//get this message from api response
                                val isSuccess = true//get this isSuccess from api response

                                callBack(
                                    Resource.success(
                                        response.body(),
                                        message,
                                        isSuccess
                                    )
                                )
                            } catch (e: Exception) {
                                callBack(
                                    Resource.error(
                                        Constants.RESPONSE_BODY_NULL,
                                        null,
                                    )
                                )
                            }


                        } else {
                            callBack(
                                Resource.error(
                                    Constants.RESPONSE_BODY_NULL,
                                    null,
                                )
                            )
                        }
                    }

                    override fun onFailure(call: Call<JsonElement?>, t: Throwable) {

                        Log.d("TAG", "getEmployees: ")
                        callBack(Resource.error(t.message.toString(), null))
                    }
                })
            } catch (e: HttpException) {
                // Returning HttpException's message
                // wrapped in Resource.Error
                Resource.error(msg = e.message ?: Constants.SOMETHING_WENT_WRONG, null)
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                Resource.error(
                    msg = Constants.CHECK_INTERNET,
                    null,
                )
            } catch (e: Exception) {
                // Returning 'Something went wrong' in case
                // of unknown error wrapped in Resource.Error
                Resource.error(msg = Constants.SOMETHING_WENT_WRONG, null)

            }
        } else {
            callBack(
                Resource.error(
                    Constants.CHECK_INTERNET,
                    null,
                )
            )

        }


    }

    fun optBooleanFromJsonOBJ(json: String?, key: String?): Boolean? {
        var res = false
        try {
            val jsonOBJ = JSONObject(json)
            if (jsonOBJ.has(key)) res = jsonOBJ.optBoolean(key, false)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return res
    }

    fun optStrFromJsonOBJ(json: String?, key: String?): String? {
        var res = ""
        try {
            val jsonOBJ = JSONObject(json)
            if (jsonOBJ.has(key)) res = jsonOBJ.optString(key, "")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return res
    }

    fun optJsonArrStrFromJsonOBJ(json: String?, key: String?): String? {
        var res = ""
        try {
            val jsonOBJ = JSONObject(json)
            if (jsonOBJ.has(key)) res = jsonOBJ.optJSONArray(key).toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return res
    }
}