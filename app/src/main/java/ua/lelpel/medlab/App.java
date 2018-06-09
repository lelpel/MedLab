package ua.lelpel.medlab;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.jakewharton.threetenabp.AndroidThreeTen;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.Date;

import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.entities.AnalysisDb;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;

/**
 * Created by bruce on 28.11.2017.
 */

public class App extends Application {
    private AppDb db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(this, AppDb.class, "labv3.db").allowMainThreadQueries().build();

        JodaTimeAndroid.init(this);
        ///AndroidThreeTen.init(this);
    }


    public AppDb getDb() {
        return db;
    }
}
