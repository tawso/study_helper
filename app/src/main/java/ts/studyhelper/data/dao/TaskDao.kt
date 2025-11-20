package ts.studyhelper.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ts.studyhelper.data.models.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks ORDER BY createdAt DESC")
    fun getAllTasks(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task): Long

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)
}