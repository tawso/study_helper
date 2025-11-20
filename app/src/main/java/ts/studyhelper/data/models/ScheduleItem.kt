package ts.studyhelper.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleItem(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    var dayOfWeek: Int, // 1..7 (Calendar.MONDAY = 2) — договоримся: 1 = Monday
    var time: String, // "09:30"
    var subject: String,
    var room: String? = null,
    var teacher: String? = null
)
