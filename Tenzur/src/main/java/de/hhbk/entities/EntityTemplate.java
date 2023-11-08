package de.hhbk.entities;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.Collection;

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

    public T getSingle(Session session, String column, String value) {
        session.beginTransaction();
        T result = (T) session.createQuery("FROM " + clazz.getName() + " WHERE :column = :value").setParameter("column", column).setParameter("value", value).uniqueResult();
        session.getTransaction().commit();

        return result;
    }

    public Collection<T> getMultiple(Session session, String column, String value) {
        session.beginTransaction();
        Collection<T> result = session.createQuery("FROM " + clazz.getName() + " WHERE :column = :value").setParameter("column", column).setParameter("value", value).list();
        session.getTransaction().commit();

        return result;
    }

    public Collection<T> getAll(Session session) {
        session.beginTransaction();
        Collection<T> result = session.createQuery("FROM " + clazz.getName(), clazz).list();
        session.getTransaction().commit();

        return result;
    }

    public T save(Session session) {
        session.beginTransaction();
        session.save(this);
        session.getTransaction().commit();
        return (T) this;
    }

    public void delete(Session session) {
        session.beginTransaction();
        session.delete(this);
        session.getTransaction().commit();
    }
}
