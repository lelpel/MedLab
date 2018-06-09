package ua.lelpel.medlab.ui.reagents;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.joda.time.LocalDate;

import java.util.List;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.AppDb;
import ua.lelpel.medlab.db.dao.ReagentDao;
import ua.lelpel.medlab.db.entities.ReagentDb;
import ua.lelpel.medlab.ui.EntityFragment;

/**
 * Created by bruce on 02.12.2017.
 */

public class ReagentRepository {
    Context context;
    AppDb db;
    ReagentDao dao;

    public ReagentRepository(Context context) {
        this.context = context;
         db = ((App) context).getDb();
         dao = db.reagentDao();
    }

    public List<ReagentDb> getData() {
        return dao.loadAll();
    }

    public void newReagent(String name, String amount) {
        ReagentDb item = new ReagentDb(name, Float.parseFloat(amount));
        dao.insert(item);
    }

    public void deleteItem(ReagentDb mItem) {
        ReagentDb dbItem = dao.findById(mItem.getId());
        dao.delete(dbItem);
    }

    public List<ReagentDb> sortAmount(int type) {
        if (type == EntityFragment.ASC) return dao.sortAmountAsc();
            else return dao.sortAmountDsc();
    }


    public List<ReagentDb> search(String arg) {
        Log.i("FIND", dao.find(arg).get(0).toString());
        return dao.find(arg);
    }

    public void updateAnalysis(int editId, String name, String amount) {
        ReagentDb item = new ReagentDb(editId, name, Float.parseFloat(amount));
        dao.update(item);
    }
}
