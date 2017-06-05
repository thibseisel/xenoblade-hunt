package fr.nihilus.xenobladechronicles.datastore;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import fr.nihilus.xenobladechronicles.model.Monster;

@Dao
public interface MonsterDao {

    @Query("SELECT * FROM monster ORDER BY level")
    LiveData<Monster[]> getAll();

    @Query("SELECT * FROM monster WHERE name LIKE :searchedName")
    LiveData<Monster[]> searchByName(String searchedName);

    @Query("SELECT DISTINCT kind FROM monster WHERE kind LIKE :search")
    LiveData<String[]> searchKinds(String search);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Monster... monster);

    @Update
    void update(Monster monster);

    @Delete
    void delete(Monster monster);
}
