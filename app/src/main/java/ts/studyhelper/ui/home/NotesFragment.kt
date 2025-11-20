package ts.studyhelper.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.observeOn
import ts.studyhelper.R
import ts.studyhelper.data.AppDatabase
import ts.studyhelper.data.models.Note
import ts.studyhelper.data.repository.NoteRepository
import ts.studyhelper.ui.notes.NoteVMFactory
import ts.studyhelper.ui.notes.NoteViewModel
import ts.studyhelper.ui.notes.NotesAdapter
import java.util.Collections.list

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = AppDatabase.getInstance(requireContext())
        val repo = NoteRepository(db.noteDao())
        viewModel = ViewModelProvider(this, NoteVMFactory(repo)).get(NoteViewModel::class.java)

        val rv: RecyclerView = view.findViewById(R.id.rvNotes)
        val fab: FloatingActionButton = view.findViewById(R.id.fabAddNote)

        adapter = NotesAdapter(emptyList(), onClick = { note -> openEditDialog(note) }, onLongClick = { note -> confirmDelete(note) })
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = adapter

        // Observe notes
        viewModel.notesLiveData.observe(viewLifecycleOwner) { list ->
            adapter.update(list)
        }

        fab.setOnClickListener { openCreateDialog() }
    }

    private fun openCreateDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Создать заметку")
        val view = layoutInflater.inflate(R.layout.dialog_note_edit, null)
        val etTitle: EditText = view.findViewById(R.id.etTitle)
        val etContent: EditText = view.findViewById(R.id.etContent)
        builder.setView(view)
        builder.setPositiveButton("Сохранить") { _, _ ->
            val t = etTitle.text.toString().ifBlank { "Без заголовка" }
            val c = etContent.text.toString()
            viewModel.addNote(t, c)
        }
        builder.setNegativeButton("Отмена", null)
        builder.show()
    }

    private fun openEditDialog(note: Note) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Редактировать заметку")
        val view = layoutInflater.inflate(R.layout.dialog_note_edit, null)
        val etTitle: EditText = view.findViewById(R.id.etTitle)
        val etContent: EditText = view.findViewById(R.id.etContent)
        etTitle.setText(note.title)
        etContent.setText(note.content)
        builder.setView(view)
        builder.setPositiveButton("Сохранить") { _, _ ->
            note.title = etTitle.text.toString()
            note.content = etContent.text.toString()
            viewModel.updateNote(note)
        }
        builder.setNegativeButton("Отмена", null)
        builder.show()
    }

    private fun confirmDelete(note: Note) {
        AlertDialog.Builder(requireContext())
            .setTitle("Удалить заметку?")
            .setMessage("Вы уверены, что хотите удалить заметку?")
            .setPositiveButton("Удалить") { _, _ -> viewModel.deleteNote(note) }
            .setNegativeButton("Отмена", null)
            .show()
    }
}