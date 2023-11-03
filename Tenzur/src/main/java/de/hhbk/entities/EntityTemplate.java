package de.hhbk.entities;

import java.io.Serializable;

public abstract class EntityTemplate implements Serializable {
    protected long id = -1;

    public EntityTemplate() {
        super();
        this.id++;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean hasId() {
        return this.id > -1;
    }
}
