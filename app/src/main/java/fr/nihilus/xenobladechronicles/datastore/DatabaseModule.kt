package fr.nihilus.xenobladechronicles.datastore

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object DatabaseModule {

    @Provides @Singleton
    fun provideDatabase(app: Application): AppDatabase = Room
        .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
        .build()

    @Provides
    fun provideMonsterDao(db: AppDatabase): MonsterDao = db.monsterDao
}
