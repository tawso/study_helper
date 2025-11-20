package ts.studyhelper.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var title: String,
    var content: String,
    var createdAt: Long = System.currentTimeMillis(),
    var updatedAt: Long = System.currentTimeMillis()
)
