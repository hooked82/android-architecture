package com.hookedroid.androidarchitecture.api.model

import com.google.gson.annotations.SerializedName

data class ResponseInfo(val count: Int,
                        val pages: Int,
                        @SerializedName("next") val nextPage: String,
                        @SerializedName("prev") val previousPage: String)