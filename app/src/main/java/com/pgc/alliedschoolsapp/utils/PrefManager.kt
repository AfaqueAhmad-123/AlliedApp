package com.pgc.alliedschoolsapp.utils

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

open class PrefManager : Methods {
    constructor() : super()


    fun clearPrefs(mActivity: Activity) {
        getPrefs(mActivity).edit().clear().commit()
    }

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences("AppPrefManager", Context.MODE_PRIVATE)
    }

    fun getMyIntPref(mActivity: Activity, prefName: String, defVal: Int): Int {
        return getPrefs(mActivity).getInt(prefName, defVal)
    }

    fun setMyIntPref(mActivity: Activity, prefName: String, value: Int) {
        getPrefs(mActivity).edit().putInt(prefName, value).apply()
    }

    fun getMyStringPref(mActivity: Activity, prefName: String, defVal: String?): String? {
        return getPrefs(mActivity).getString(prefName, defVal)
    }

    fun setMyStringPref(mActivity: Activity, prefName: String, value: String) {
        getPrefs(mActivity).edit().putString(prefName, value).apply()
    }

    fun setMyStringPref(mContext: Context, prefName: String, value: String) {
        getPrefs(mContext).edit().putString(prefName, value).apply()
    }

    fun getMyBooleanPref(mActivity: Activity, prefName: String, defVal: Boolean): Boolean {
        return getPrefs(mActivity).getBoolean(prefName, defVal)
    }

    fun getMyBooleanPref(mActivity: Context, prefName: String, defVal: Boolean): Boolean {
        return getPrefs(mActivity).getBoolean(prefName, defVal)
    }

    fun setMyBooleanPref(mActivity: Activity, prefName: String, value: Boolean) {
        getPrefs(mActivity).edit().putBoolean(prefName, value).apply()
    }

    fun getMyLongPref(mActivity: Activity, prefName: String, defVal: Long): Long {
        return getPrefs(mActivity).getLong(prefName, defVal)
    }

    fun setMyLongPref(mActivity: Activity, prefName: String, value: Long) {
        getPrefs(mActivity).edit().putLong(prefName, value).apply()
    }


    fun removePref(mActivity: Activity, prefName: String) {
        getPrefs(mActivity).edit().remove(prefName).apply()
    }



}