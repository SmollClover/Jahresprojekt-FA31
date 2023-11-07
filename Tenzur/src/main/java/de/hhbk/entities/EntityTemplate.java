package de.hhbk.entities;

import org.hibernate.Session;

import java.io.Serializable;

public class EntityTemplate<T> implements Serializable {
    private final Class<T> clazz;

    public EntityTemplate(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T getById(Session session, long id) {
        session.beginTransaction();
        T result = session.get(clazz, id);
        session.getTransaction().commit();
        return result;
    }

    public void save(Session session) {
        session.beginTransaction();
        session.save(this);
        session.getTransaction().commit();
    }

    public void delete(Session session) {
        session.beginTransaction();
        session.delete(this);
        session.getTransaction().commit();
    }
}
