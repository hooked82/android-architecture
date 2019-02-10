package com.hookedroid.androidarchitecture.api.model

data class ApiResponse<T>(val info: ResponseInfo,
                          val results: List<T>)