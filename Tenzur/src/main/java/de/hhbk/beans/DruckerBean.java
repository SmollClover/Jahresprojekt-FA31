package de.hhbk.beans;

import de.hhbk.model.Drucker;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;


@Named(value = "printer")
@SessionScoped
public class DruckerBean extends BeanTemplate {
    //-------------------------------------------------------------------------
    //  Item
    //-------------------------------------------------------------------------
    protected Drucker item = null;
    //-------------------------------------------------------------------------
    //  List
    //-------------------------------------------------------------------------
    protected List<Drucker> itemList = null;


    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------
    public DruckerBean() {
        super();
    }

    @PostConstruct
    public void init() {
        resetItem();
        getItemList().add(new Drucker("880815", "Brother", "DCP 8490"));
        getItemList().add(new Drucker("990815", "Canon", "MX-925"));
        getItemList().add(new Drucker("222888", "HP", "DeskJet 9000"));
    }

    public Drucker getItem() {
        return item;
    }

    public void setItem(Drucker item) {
        this.item = item;
    }

    public void resetItem() {
        item = new Drucker();
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

    public void removeItem(Drucker d) {
        if (d != null && getItemList().contains(d)) {
            getItemList().remove(d);
        }
    }


}
