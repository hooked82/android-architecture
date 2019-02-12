package com.hookedroid.androidarchitecture.data

data class NetworkState private constructor(
    val status: Status,
    val message: String? = null) {
    companion object {
        val LOADED = NetworkState(Status.SUCCESS)
        val LOADING = NetworkState(Status.RUNNING)
        val ENDED = NetworkState(Status.ENDED)

        fun error(message: String?) = NetworkState(Status.FAILED, message)
    }
}

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED,
    ENDED
}