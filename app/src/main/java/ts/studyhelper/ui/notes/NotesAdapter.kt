package ts.studyhelper.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.widget.TextView
import ts.studyhelper.R
import ts.studyhelper.data.models.Note

class NotesAdapter(
    private var items: List<Note> = emptyList(),
    private val onClick: (Note) -> Unit,
    private val onLongClick: (Note) -> Unit
) : RecyclerView.Adapter<NotesAdapter.VH>() {

    fun update(newItems: List<Note>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvContent: TextView = view.findViewById(R.id.tvContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val n = items[position]
        holder.tvTitle.text = n.title
        holder.tvContent.text = n.content
        holder.itemView.setOnClickListener { onClick(n) }
        holder.itemView.setOnLongClickListener { onLongClick(n); true }
    }

    override fun getItemCount() = items.size
}