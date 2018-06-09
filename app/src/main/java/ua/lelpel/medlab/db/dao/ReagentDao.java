package ua.lelpel.medlab.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ua.lelpel.medlab.db.entities.ReagentDb;

/**
 * Created by bruce on 02.12.2017.
 */
@Dao
public interface ReagentDao {
    @Insert
    void insert(ReagentDb item);

    @Query("SELECT * FROM REAGENTS")
    List<ReagentDb> loadAll();

    @Query("SELECT * FROM REAGENTS WHERE ID = :id")
    ReagentDb findById(int id);

    @Delete
    void delete(ReagentDb dbItem);

    @Update
    void update(ReagentDb item);

    @Query("SELECT * FROM REAGENTS ORDER BY amount ASC")
    List<ReagentDb> sortAmountAsc();

    @Query("SELECT * FROM REAGENTS ORDER BY amount DESC")
    List<ReagentDb> sortAmountDsc();

    @Query("SELECT * FROM REAGENTS WHERE NAME LIKE '%' || :s || '%'")
    List<ReagentDb> find(String s);
}
