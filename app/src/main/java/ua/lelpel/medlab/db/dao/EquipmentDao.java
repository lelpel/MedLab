package ua.lelpel.medlab.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.ui.entities.AnalysisUi;
import ua.lelpel.medlab.ui.entities.EquipmentUi;

/**
 * Created by bruce on 02.12.2017.
 */
@Dao
public interface EquipmentDao {
    @Insert
    void insert(EquipmentDb item);

    @Query("SELECT * FROM EQUIPMENTS")
    EquipmentDb[] loadAll();

    @Delete
    void delete(EquipmentDb item);

    @Update
    void update(EquipmentDb item);

    @Query("SELECT EQUIPMENTS.ID AS id, " +
            "EQUIPMENTS.NAME AS name, " +
            "TYPES.NAME AS typeName, " +
            "REAGENTS.NAME AS reagentName " +
            "FROM EQUIPMENTS " +
            "JOIN TYPES ON EQUIPMENTS.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID")
    List<EquipmentUi> loadAllMapped();

    @Query("SELECT * FROM equipments WHERE ID = :id")
    EquipmentDb findById(int id);
}
