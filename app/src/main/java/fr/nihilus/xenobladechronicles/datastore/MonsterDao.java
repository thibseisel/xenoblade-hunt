package fr.nihilus.xenobladechronicles.datastore;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import fr.nihilus.xenobladechronicles.monsters.Monster;

@Dao
public interface MonsterDao {

    @Query("SELECT * FROM monster ORDER BY name")
    LiveData<Monster[]> getAll();

    @Query("SELECT * FROM monster WHERE name LIKE :searchedName")
    LiveData<Monster[]> searchByName(String searchedName);

    @Insert
    Long insert(Monster monster);

    @Update
    void update(Monster monster);

    @Delete
    void delete(Monster monster);
}
