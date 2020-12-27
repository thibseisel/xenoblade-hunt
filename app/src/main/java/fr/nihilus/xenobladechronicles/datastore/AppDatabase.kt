package fr.nihilus.xenobladechronicles.datastore

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.nihilus.xenobladechronicles.model.Monster

@Database(entities = [Monster::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val monsterDao: MonsterDao

    companion object {
        const val DATABASE_NAME = "xenoblade.db"
    }
}
