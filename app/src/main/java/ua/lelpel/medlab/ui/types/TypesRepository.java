package ua.lelpel.medlab.ui.types;

import android.content.Context;
import android.util.Log;

import java.util.List;

import ua.lelpel.medlab.App;
import ua.lelpel.medlab.db.dao.AnalysisTypeDao;
import ua.lelpel.medlab.db.entities.AnalysisTypeDb;
import ua.lelpel.medlab.ui.entities.AnalysisUi;

/**
 * Created by bruce on 02.12.2017.
 */

public class TypesRepository {
    Context context;
    AnalysisTypeDao dao;

    public TypesRepository(Context context) {
        this.context = context;
         dao = ((App) context).getDb().typeDao();
    }

    public List<AnalysisTypeDb> getData() {
        return dao.loadAll();
    }


    public void deleteItem(AnalysisTypeDb mItem) {
        AnalysisTypeDb dbItem = dao.findById(mItem.getId());
        dao.delete(dbItem);
    }

    public void updateAnalysis(String s, int id) {
        AnalysisTypeDb item = new AnalysisTypeDb(id, s);
        dao.update(item);
    }
}
