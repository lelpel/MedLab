package ua.lelpel.medlab.ui.types;

import android.content.Context;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.dao.AnalysisTypeDao;
import ua.lelpel.medlab.db.dao.StaffDao;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.db.entities.EquipmentDb;
import ua.lelpel.medlab.db.entities.StaffMemberDb;

/**
 * Created by bruce on 02.12.2017.
 */

public class AddTypeRepository {
    Context context;
    AnalysisTypeDao dao;

    public AddTypeRepository(Context context) {
        this.context = context;
        dao = ((App) context).getDb().typeDao();
    }

    public void add(String s) {
        dao.insert(new AnalysisTypeDb(s));
    }

    public void update(String s, int editid) {
        dao.update(new AnalysisTypeDb(editid, s));
    }
}
