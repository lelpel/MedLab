package ua.lelpel.medlab.ui.analyzes;

import android.content.Context;

import java.util.List;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;

/**
 * Created by bruce on 02.12.2017.
 */

public class AddAnalysisRepository {
    Context context;
    AppDb db;

    public AddAnalysisRepository(Context context) {
        this.context = context;
        db = ((App) context).getDb();
    }

    public EquipmentDb[] getEquipment() {
        return db.equipmentDao().loadAll();
    }

    public List<AnalysisTypeDb> getTypes() {
        return db.typeDao().loadAll();
    }

    public List<StaffMemberDb> getStaff() {
        return db.staffDao().loadAll();
    }
}
