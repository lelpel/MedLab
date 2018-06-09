package ua.lelpel.medlab.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import ua.lelpel.medlab.db.dao.AnalysisDao;
import ua.lelpel.medlab.db.dao.AnalysisTypeDao;
import ua.lelpel.medlab.db.dao.EquipmentDao;
import ua.lelpel.medlab.db.dao.ReagentDao;
import ua.lelpel.medlab.db.dao.StaffDao;
import ua.lelpel.medlab.db.entities.AnalysisDb;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;

/**
 * Created by bruce on 28.11.2017.
 */

@Database(version = 3, entities = {AnalysisDb.class, AnalysisTypeDb.class, EquipmentDb.class, ReagentDb.class, StaffMemberDb.class}, exportSchema = false)
@TypeConverters(LocalDateConverter.class)
public abstract class AppDb extends RoomDatabase {
    private static AppDb INSTANCE;

    public abstract AnalysisDao analyzesDao();
    public abstract AnalysisTypeDao typeDao();
    public abstract EquipmentDao equipmentDao();
    public abstract ReagentDao reagentDao();
    public abstract StaffDao staffDao();
}
