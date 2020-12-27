package fr.nihilus.xenobladechronicles.datastore

import androidx.room.*
import fr.nihilus.xenobladechronicles.model.Monster
import kotlinx.coroutines.flow.Flow

@Dao
interface MonsterDao {

    @Query("SELECT * FROM monster ORDER BY level")
    fun getAll(): Flow<List<Monster>>

    @Query("SELECT * FROM monster WHERE name LIKE :searchedName")
    fun searchByName(searchedName: String): Flow<List<Monster>>

    @Query("SELECT DISTINCT kind FROM monster WHERE kind LIKE :search")
    fun searchKinds(search: String): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(monster: Monster)

    @Update
    suspend fun update(monster: Monster)

    @Delete
    suspend fun delete(monster: Monster)
}
