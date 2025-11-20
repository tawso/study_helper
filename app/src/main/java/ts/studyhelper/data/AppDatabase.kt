package ts.studyhelper.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import ts.studyhelper.data.dao.NoteDao
import ts.studyhelper.data.dao.ScheduleDao
import ts.studyhelper.data.dao.TaskDao

import ts.studyhelper.data.models.Note
import ts.studyhelper.data.models.ScheduleItem
import ts.studyhelper.data.models.Task

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java, "study_helper.db")
                .fallbackToDestructiveMigration() // можно заменить на миграции
                .build()
    }
}