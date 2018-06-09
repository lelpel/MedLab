package ua.lelpel.medlab.ui.entities;

import android.arch.persistence.room.Ignore;

/**
 * Created by bruce on 05.12.2017.
 */

public class EquipmentUi {
    private int id;
    private String name;
    private String typeName;
    private String reagentName;

    @Ignore
    public EquipmentUi(int id, String name, String typeName, String reagentName) {
        this.id = id;
        this.name = name;
        this.typeName = typeName;
        this.reagentName = reagentName;
    }

    public EquipmentUi(String name, String typeName, String reagentName) {
        this.name = name;
        this.typeName = typeName;
        this.reagentName = reagentName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getReagentName() {
        return reagentName;
    }

    public void setReagentName(String reagentName) {
        this.reagentName = reagentName;
    }
}
