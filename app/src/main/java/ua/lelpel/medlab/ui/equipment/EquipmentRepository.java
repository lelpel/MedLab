package ua.lelpel.medlab.ui.equipment;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.joda.time.LocalDate;

import java.util.List;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;
import ua.lelpel.medlab.ui.EntityFragment;
import ua.lelpel.medlab.ui.entities.AnalysisUi;
import ua.lelpel.medlab.ui.entities.EquipmentUi;
import ua.lelpel.medlab.ui.entities.EquipmentUi;

/**
 * Created by bruce on 02.12.2017.
 */

public class EquipmentRepository {
    Context context;
    AppDb db;

    public EquipmentRepository(Context context) {
        this.context = context;
         db = ((App) context).getDb();
    }

    public List<EquipmentUi> getData() {
        return db.equipmentDao().loadAllMapped();
    }

    public void newEquipment(String name, AnalysisTypeDb type, ReagentDb reagent) {
        EquipmentDb item = new EquipmentDb(name, type.getId(), reagent.getId());
        db.equipmentDao().insert(item);
    }

    public void updateEquipment(String name, AnalysisTypeDb type, ReagentDb reagent) {
        EquipmentDb item = new EquipmentDb(name, type.getId(), reagent.getId());
        db.equipmentDao().update(item);
    }

    public void deleteItem(EquipmentUi uiItem) {
        EquipmentDb item = db.equipmentDao().findById(uiItem.getId());
        db.equipmentDao().delete(item);
    }
}
