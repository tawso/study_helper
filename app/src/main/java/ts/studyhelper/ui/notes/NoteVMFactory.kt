package ts.studyhelper.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ts.studyhelper.data.repository.NoteRepository

class NoteVMFactory(private val repo: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}