package com.pgc.alliedschoolsapp.utils


class SingletonClass : PrefManager() {

    companion object {
        var ST: SingletonClass? = null


        fun getInstance(): SingletonClass {
            if (ST == null)
                ST = SingletonClass()
            return ST as SingletonClass
        }
    }
}