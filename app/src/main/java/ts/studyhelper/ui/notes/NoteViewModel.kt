package ts.studyhelper.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ts.studyhelper.data.models.Note
import ts.studyhelper.data.repository.NoteRepository

class NoteViewModel(private val repo: NoteRepository) : ViewModel() {
    val notes: StateFlow<List<Note>> = repo.getAll()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val notesLiveData = repo.getAll().asLiveData()


    fun addNote(title: String, content: String, onResult: (Long) -> Unit = {}) {
        viewModelScope.launch {
            val note = Note(title = title, content = content)
            val id = repo.insert(note)
            onResult(id)
        }
    }

    fun updateNote(note: Note) {
        note.updatedAt = System.currentTimeMillis()
        viewModelScope.launch { repo.update(note) }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch { repo.delete(note) }
    }
}