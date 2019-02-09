package com.hookedroid.androidarchitecture.notes

import androidx.lifecycle.ViewModel
import com.hookedroid.androidarchitecture.data.repository.NoteRepository
import javax.inject.Inject

class NotesViewModel @Inject constructor(noteRepository: NoteRepository) : ViewModel() {
}