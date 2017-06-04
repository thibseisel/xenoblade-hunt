package fr.nihilus.xenobladechronicles.datastore;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import fr.nihilus.xenobladechronicles.model.Monster;

@Database(entities = {Monster.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "xenoblade.db";

    public abstract MonsterDao monsterDao();
}
