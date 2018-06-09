package ua.lelpel.medlab.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.joda.time.LocalDate;

import java.util.List;

import ua.lelpel.medlab.db.entities.AnalysisDb;
import ua.lelpel.medlab.ui.entities.AnalysisUi;

/**
 * Created by bruce on 28.11.2017.
 */
@Dao
public interface AnalysisDao {
    @Insert
    void insert(AnalysisDb item);

    @Query("SELECT * FROM analyzes")
    List<AnalysisDb> loadAll();

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID")
    List<AnalysisUi> loadAllMapped();

    @Query("SELECT * FROM ANALYZES WHERE ID = :id")
    AnalysisDb findById(long id);

    @Delete
    void delete(AnalysisDb delete);

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "ORDER BY id ASC")
    List<AnalysisUi> sortIdAsc();

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "ORDER BY id DESC")
    List<AnalysisUi> sortIdDesc();

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "ORDER BY date ASC")
    List<AnalysisUi> sortDateAsc();

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "ORDER BY date DESC")
    List<AnalysisUi> sortDateDesc();

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "ORDER BY typeName ASC")
    List<AnalysisUi> sortTypeAsc();

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "ORDER BY typeName ASC")
    List<AnalysisUi> sortTypeDsc();

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "WHERE ANALYZES.DATE < :d")
    List<AnalysisUi> filterByDateLessThan(LocalDate d);

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "WHERE ANALYZES.DATE > :d")
    List<AnalysisUi> filterByDateGreaterThan(LocalDate d);

    @Query("SELECT ANALYZES.ID AS id, " +
            "ANALYZES.DATE AS date, " +
            "REAGENTS.NAME AS reagentName, " +
            "REAGENTS.AMOUNT AS reagentAmount, " +
            "STAFF.FIRST_NAME || ' ' || STAFF.LAST_NAME AS staffName, " +
            "TYPES.NAME AS typeName, " +
            "ANALYZES.RESULT AS result, " +
            "EQUIPMENTS.NAME AS equipmentName " +
            "FROM ANALYZES " +
            "JOIN TYPES ON ANALYZES.FK_TYPE_ID = TYPES.ID " +
            "JOIN REAGENTS ON EQUIPMENTS.FK_REAGENT_ID = REAGENTS.ID " +
            "JOIN EQUIPMENTS ON ANALYZES.FK_EQUIPMENT_ID = EQUIPMENTS.ID " +
            "JOIN STAFF ON ANALYZES.FK_STAFF_ID = STAFF.ID " +
            "WHERE staffName LIKE '%' || :s || '%' " +
            "OR result LIKE '%' || :s || '%' " +
            "OR reagentName LIKE '%' || :s || '%' " +
            "OR typeName LIKE '%' || :s || '%'")
    List<AnalysisUi> find(String s);

    @Update
    void update(AnalysisDb item);
}
