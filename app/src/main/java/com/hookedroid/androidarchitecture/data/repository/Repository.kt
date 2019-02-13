package com.hookedroid.androidarchitecture.data.repository

import androidx.lifecycle.LiveData
import com.hookedroid.androidarchitecture.data.Listing

interface Repository<T> {
    fun getById(id: Int): LiveData<T>
    fun getByPage(page: Int): Listing<T>
}