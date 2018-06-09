package ua.lelpel.medlab.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by bruce on 27.11.2017.
 */

@Entity(tableName = "reagents")
public class ReagentDb {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private float amount;

    public ReagentDb(String name, float amount) {
        this.name = name;
        this.amount = amount;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Ignore
    public ReagentDb(int id, String name, float amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name;
    }
}
