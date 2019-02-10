package com.hookedroid.androidarchitecture.api.model

data class ResponseInfo(val count: Int,
                        val pages: Int,
                        val nextPage: String,
                        val previousPage: String)