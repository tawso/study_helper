package ts.studyhelper.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ts.studyhelper.data.models.ScheduleItem

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule ORDER BY dayOfWeek, time")
    fun getAll(): Flow<List<ScheduleItem>>

    @Query("SELECT * FROM schedule WHERE dayOfWeek = :day ORDER BY time")
    fun getByDay(day: Int): Flow<List<ScheduleItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ScheduleItem): Long

    @Update
    suspend fun update(item: ScheduleItem)

    @Delete
    suspend fun delete(item: ScheduleItem)
}