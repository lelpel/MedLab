package ua.lelpel.medlab.ui.equipment;

import android.content.Context;

import java.util.List;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;

/**
 * Created by bruce on 02.12.2017.
 */

public class AddEquipmentRepository {
    Context context;
    AppDb db;

    public AddEquipmentRepository(Context context) {
        this.context = context;
        db = ((App) context).getDb();
    }

    public List<AnalysisTypeDb> getTypes() {
        return db.typeDao().loadAll();
    }

    public List<ReagentDb> getReagents() {return db.reagentDao().loadAll();}

}
