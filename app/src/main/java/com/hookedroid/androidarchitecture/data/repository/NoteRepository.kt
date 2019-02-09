package com.hookedroid.androidarchitecture.data.repository

import com.hookedroid.androidarchitecture.data.dao.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
}