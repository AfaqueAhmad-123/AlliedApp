package com.pgc.alliedschoolsapp.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.examplecleanarch.resource.Resource
import com.google.gson.JsonElement
import com.pgc.alliedschoolsapp.R
import org.json.JSONException
import org.json.JSONObject
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern
import kotlin.collections.ArrayList

open class Methods : Constants() {


    fun isTokenExpire(timeStamp: Long): Boolean {
        val milliseconds = System.currentTimeMillis()
        val currentTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
        return timeStamp < currentTimeSeconds
    }

    fun showToast(context: Context, msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showPopup(
        context: Context,
        v: View,
        menuRef: Int,
        listener: PopupMenu.OnMenuItemClickListener
    ) {
        val popup = PopupMenu(context, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(menuRef, popup.menu)
        popup.setOnMenuItemClickListener(listener)
        popup.show()
    }


    fun showToastAndFinish(requireActivity: Activity) {

        showToast(requireActivity, requireActivity.getString(R.string.something_went_wrong))
        requireActivity.finish()
    }
  fun somethingWentWrong(requireActivity: Activity) {

        showToast(requireActivity, requireActivity.getString(R.string.something_went_wrong))
    }


    fun getError(response: Resource<JsonElement>): String {

        var text = "No data found"

        if (!response.message.isNullOrEmpty()) {

            if (response.message.contains("End of input")) {
                text = "No data found"

            } else {
                text = response.message
            }


        } else {

            text = "No data found"

        }

        return text

    }


}