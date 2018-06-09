package ua.lelpel.medlab.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;

/**
 * Created by bruce on 02.12.2017.
 */
@Dao
public interface StaffDao {
    @Insert
    void insert(StaffMemberDb person);

    @Query("SELECT * FROM STAFF")
    List<StaffMemberDb> loadAll();

    @Query("SELECT * FROM STAFF WHERE ID = :id")
    StaffMemberDb findById(int id);

    @Delete
    void delete(StaffMemberDb dbItem);

    @Update
    void update(StaffMemberDb item);

    @Query("SELECT * FROM STAFF WHERE ID = :id")
    List<StaffMemberDb> find(String id);

    @Query("SELECT * FROM STAFF ORDER BY date ASC")
    List<StaffMemberDb> sortDateAsc();

    @Query("SELECT * FROM STAFF ORDER BY date DESC")
    List<StaffMemberDb> sortDateDsc();

    @Query("SELECT * FROM STAFF ORDER BY last_name ASC")
    List<StaffMemberDb> sortNameAsc();

    @Query("SELECT * FROM STAFF ORDER BY last_name DESC")
    List<StaffMemberDb> sortNameDsc();
}
