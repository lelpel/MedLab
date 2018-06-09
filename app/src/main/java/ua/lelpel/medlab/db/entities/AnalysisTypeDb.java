package ua.lelpel.medlab.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by bruce on 27.11.2017.
 */

@Entity(tableName = "types")
public class AnalysisTypeDb {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;

    public AnalysisTypeDb(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public AnalysisTypeDb(String s) {
        this.name = s;
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

    @Override
    public String toString() {
        return name;
    }
}
