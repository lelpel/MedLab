package ua.lelpel.medlab.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ua.lelpel.medlab.db.entities.AnalysisTypeDb;

/**
 * Created by bruce on 02.12.2017.
 */

@Dao
public interface AnalysisTypeDao {
    @Insert
    void insert(AnalysisTypeDb item);

    @Query("SELECT * FROM TYPES")
    List<AnalysisTypeDb> loadAll();

    @Delete
    void delete(AnalysisTypeDb dbItem);

    @Query("SELECT * FROM TYPES WHERE ID = :id")
    AnalysisTypeDb findById(long id);

    @Update
    void update(AnalysisTypeDb item);
}
