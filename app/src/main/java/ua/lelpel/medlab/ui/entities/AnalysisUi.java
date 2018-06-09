package ua.lelpel.medlab.ui.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by bruce on 01.12.2017.
 */

public class AnalysisUi {
    public long id;

    public String date;
    public  String result;

    public String staffName;
    public String typeName;
    public String equipmentName;
    public String reagentName;
    public float reagentAmount;

    public AnalysisUi(String date, String result, String staffName, String typeName, String equipmentName, String reagentName, float reagentAmount) {
        this.date = date;
        this.result = result;
        this.staffName = staffName;
        this.typeName = typeName;
        this.equipmentName = equipmentName;
        this.reagentName = reagentName;
        this.reagentAmount = reagentAmount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public static List<AnalysisUi> dummy() {
        List<AnalysisUi> l = new ArrayList<>();
        l.add(new AnalysisUi("01.01.2017", "Положительный", "Сидоров Иван Сидорович", "Алкотест", "Дыхалка", "Воздух", 1.0f));
        l.add(new AnalysisUi("02.02.2018", "КЕК", "Лол", "Ору", "Пекц", "Хекц", 1.0f));
        return l;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getReagentName() {
        return reagentName;
    }

    public void setReagentName(String reagentName) {
        this.reagentName = reagentName;
    }

    public float getReagentAmount() {
        return reagentAmount;
    }

    public void setReagentAmount(float reagentAmount) {
        this.reagentAmount = reagentAmount;
    }

    @Override
    public String toString() {
        return "AnalysisUi{" +
                "staffName='" + staffName + '\'' +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
