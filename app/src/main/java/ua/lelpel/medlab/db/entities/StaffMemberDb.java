package ua.lelpel.medlab.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.joda.time.LocalDate;

/**
 * Created by bruce on 27.11.2017.
 */

@Entity(tableName = "staff")
public class StaffMemberDb {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String first_name;
    private String last_name;
    private LocalDate date;

    public StaffMemberDb(String first_name, String last_name, LocalDate date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Ignore
    public StaffMemberDb(int id, String first_name, String last_name, LocalDate date) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.date = date;
    }

    @Override
    public String toString() {
        return first_name;
    }
}
