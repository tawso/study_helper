package ts.studyhelper.data.repository

import kotlinx.coroutines.flow.Flow
import ts.studyhelper.data.dao.NoteDao
import ts.studyhelper.data.models.Note

class NoteRepository(private val dao: NoteDao) {
    fun getAll(): Flow<List<Note>> = dao.getAllNotes()
    suspend fun getById(id: Long): Note? = dao.getNoteById(id)
    suspend fun insert(note: Note): Long = dao.insert(note)
    suspend fun update(note: Note) = dao.update(note)
    suspend fun delete(note: Note) = dao.delete(note)
}