package ts.studyhelper.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String,
    var description: String? = null,
    var dueAt: Long? = null,
    var isDone: Boolean = false,
    var createdAt: Long = System.currentTimeMillis()
)
