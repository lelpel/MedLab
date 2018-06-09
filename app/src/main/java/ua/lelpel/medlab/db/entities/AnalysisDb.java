package ua.lelpel.medlab.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.LocalDate;

import java.util.Date;

/**
 * Created by bruce on 27.11.2017.
 */

@Entity(tableName = "analyzes", foreignKeys = {@ForeignKey(entity = AnalysisTypeDb.class, parentColumns = "id", childColumns = "fk_type_id"),
        @ForeignKey(entity = StaffMemberDb.class,parentColumns = "id", childColumns = "fk_staff_id"),
        @ForeignKey(entity = EquipmentDb.class, parentColumns = "id", childColumns = "fk_equipment_id")})
public class AnalysisDb {
    //PK
    @PrimaryKey(autoGenerate = true)
    private long id;

    private LocalDate date;
    private String result;

    //FK
    @ColumnInfo(name = "fk_equipment_id")
    private int equipmentId;
    @ColumnInfo(name = "fk_staff_id")
    private int staffId;
    @ColumnInfo(name = "fk_type_id")
    private int typeId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public AnalysisDb(LocalDate date, String result, int equipmentId, int staffId, int typeId) {
        this.date = date;
        this.result = result;
        this.equipmentId = equipmentId;
        this.staffId = staffId;
        this.typeId = typeId;
    }

    @Ignore
    public AnalysisDb(long id, LocalDate date, String result, int equipmentId, int staffId, int typeId) {
        this.id = id;
        this.date = date;
        this.result = result;
        this.equipmentId = equipmentId;
        this.staffId = staffId;
        this.typeId = typeId;
    }
}
