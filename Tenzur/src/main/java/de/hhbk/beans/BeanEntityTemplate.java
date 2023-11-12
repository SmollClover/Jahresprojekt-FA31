package de.hhbk.beans;

import de.hhbk.entities.EntityTemplate;
import de.hhbk.managers.DatabaseManager;
import org.hibernate.Session;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

public class BeanEntityTemplate<T extends EntityTemplate> extends BeanTemplate {
    private Class<T> clazz = null;
    private Collection<T> itemList = new ArrayList<T>();
    private T item = null;

    public BeanEntityTemplate(@NotNull Class<T> clazz) {
        super(clazz.getSimpleName());
        this.clazz = clazz;
    }

    public void loadItemList() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        DatabaseManager DB = (DatabaseManager) this.ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        this.itemList = clazz.getDeclaredConstructor().newInstance().getList(session);
        session.close();
    }

    public Collection<T> getItemList() {
        return this.itemList;
    }

    public T getItem() {
        return this.item;
    }

    public void setItem(@NotNull T item) {
        this.item = item;
    }

    public void resetItem() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.item = clazz.getDeclaredConstructor().newInstance();
    }

    public void addItem() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // System.out.println(this.itemList.contains(this.item));
        System.out.println(this.item);

        // if (!this.itemList.contains(this.item)) return;

        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();


        this.item.save(session);
        session.close();

        this.itemList.add(this.item);
        this.resetItem();
    }

    public void removeItem(@NotNull T item) {
        if (!this.itemList.contains(item)) return;
        DatabaseManager DB = (DatabaseManager) ctx.getAttribute("DB");
        Session session = DB.getSessionFactory().openSession();
        item.delete(session);
        session.close();
        this.itemList.remove(item);
    }
}
