package de.hhbk.beans;

import de.hhbk.model.Raum;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named(value = "rooms")
@SessionScoped
public class RaumBean extends BeanTemplate {
    //-------------------------------------------------------------------------
    //  Item
    //-------------------------------------------------------------------------
    protected Raum item = null;
    //-------------------------------------------------------------------------
    //  List
    //-------------------------------------------------------------------------
    protected List<Raum> itemList = null;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------
    public RaumBean() {
        super();
    }

    @PostConstruct
    public void init() {
        resetItem();
        getItemList().add(new Raum("A120", "IT Fachraum"));
        getItemList().add(new Raum("A121", "Lehrerzimmer"));
        getItemList().add(new Raum("A122", "IT Fachraum"));
        getItemList().add(new Raum("A123", "IT Fachraum"));
    }

    public Raum getItem() {
        return item;
    }

    public void setItem(Raum item) {
        this.item = item;
    }

    public void resetItem() {
        item = new Raum();
        ;
    }

    public List getItemList() {
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        return itemList;
    }

    public void setItemList(List itemList) {
        this.itemList = itemList;
    }

    public void saveItem() {
        if (item != null && item.hasId() && !getItemList().contains(item)) {
            getItemList().add(item);
        }
    }

    public void removeItem(Raum r) {
        if (r != null && getItemList().contains(r)) {
            getItemList().remove(r);
        }
    }


}
