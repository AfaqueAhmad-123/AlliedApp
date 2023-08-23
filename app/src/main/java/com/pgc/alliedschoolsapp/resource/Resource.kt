package com.example.examplecleanarch.resource


data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val isSuccess: Boolean,

    ) {
    companion object {

        fun <T> success(
            data: T?,
            message: String?,
            isSuccess: Boolean
        ): Resource<T> {
            return Resource(Status.SUCCESS, data, message, isSuccess = isSuccess)
        }

        fun <T> error(msg: String, data: T? = null, ): Resource<T> {
            return Resource(Status.ERROR, data, msg, false)
        }

        fun <T> loading(data: T? = null, ): Resource<T> {
            return Resource(Status.LOADING, data, null, false)
        }

    }
}


enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
