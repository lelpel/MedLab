package ua.lelpel.medlab.db.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by bruce on 27.11.2017.
 */
@Entity(tableName = "equipments", foreignKeys = {@ForeignKey(entity = AnalysisTypeDb.class, parentColumns = "id", childColumns = "fk_type_id"),
@ForeignKey(entity = ReagentDb.class, parentColumns = "id", childColumns = "fk_reagent_id")})
public class EquipmentDb {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    //TODO: FKs
    //FK
    @ColumnInfo(name = "fk_type_id")
    private int typeId;
    @ColumnInfo(name = "fk_reagent_id")
    private int reagentId;

    @Ignore
    public EquipmentDb(int id, String name, int typeId, int reagentId) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.reagentId = reagentId;
    }

    public EquipmentDb(String name, int typeId, int reagentId) {
        this.name = name;
        this.typeId = typeId;
        this.reagentId = reagentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getReagentId() {
        return reagentId;
    }

    public void setReagentId(int reagentId) {
        this.reagentId = reagentId;
    }

    @Override
    public String toString() {
        return name;
    }
}
