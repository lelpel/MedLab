package ua.lelpel.medlab.ui.analyzes;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.joda.time.LocalDate;

import java.util.Date;
import java.util.List;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.entities.AnalysisDb;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;
import ua.lelpel.medlab.ui.EntityFragment;
import ua.lelpel.medlab.ui.entities.AnalysisUi;

/**
 * Created by bruce on 02.12.2017.
 */

public class AnalyzesRepository {
    Context context;
    AppDb db;

    public AnalyzesRepository(Context context) {
        this.context = context;
         db = ((App) context).getDb();
    }

    public List<AnalysisUi> getData() {
        return db.analyzesDao().loadAllMapped();
    }


    public void newAnalysis(String result, LocalDate date, AnalysisTypeDb type, StaffMemberDb staff, EquipmentDb eq) {
        AnalysisDb item = new AnalysisDb(date, result, eq.getId(), staff.getId(), type.getId());
        db.analyzesDao().insert(item);
    }

    public void deleteItem(AnalysisUi mItem) {
        AnalysisDb dbItem = db.analyzesDao().findById(mItem.getId());
        db.analyzesDao().delete(dbItem);
    }

    public List<AnalysisUi> sortId(int type) {
        if (type == EntityFragment.ASC) return db.analyzesDao().sortIdAsc();
        else return db.analyzesDao().sortIdDesc();
    }

    public List<AnalysisUi> sortType(int type) {
        if (type == EntityFragment.ASC) return db.analyzesDao().sortTypeAsc();
            else return db.analyzesDao().sortTypeDsc();
    }


    public List<AnalysisUi> sortDate(int type) {
        if (type == EntityFragment.ASC) return db.analyzesDao().sortDateAsc();
        else return db.analyzesDao().sortDateDesc();
    }

    public List<AnalysisUi> filterDateLessThan(LocalDate d) {
        return db.analyzesDao().filterByDateLessThan(d);
    }

    public List<AnalysisUi> filterDateGreaterThan(LocalDate d) {
        return db.analyzesDao().filterByDateGreaterThan(d);
    }

    public List<AnalysisUi> search(String arg) {
        return db.analyzesDao().find(arg);

    }

    public void updateAnalysis(long editId, String result, LocalDate date, AnalysisTypeDb type, StaffMemberDb staff, EquipmentDb eq) {
        AnalysisDb item = new AnalysisDb(editId, date, result, eq.getId(), staff.getId(), type.getId());
        db.analyzesDao().update(item);
    }
}
