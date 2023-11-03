package de.hhbk.beans;

import de.hhbk.model.Benutzer;
import de.hhbk.model.enums.PersonAnrede;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Named(value = "benutzer")
@SessionScoped
public class BenutzerBean extends BeanTemplate {
    //-------------------------------------------------------------------------
    //  Item
    //-------------------------------------------------------------------------
    protected Benutzer item = null;
    //-------------------------------------------------------------------------
    //  List
    //-------------------------------------------------------------------------
    protected List<Benutzer> itemList = null;


    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------
    public BenutzerBean() {
        super();
    }

    @PostConstruct
    public void init() {
        resetItem();
        getItemList().add(new Benutzer(PersonAnrede.MR, "Bill", "Gates", "bill.gates@microsoft.com", "bigmoney"));
        getItemList().add(new Benutzer(PersonAnrede.MR, "Teddy", "Baer", "t.baer@kuschel.com", "knuffig"));
        getItemList().add(new Benutzer(PersonAnrede.MR, "Ernst", "Haft", "e.haft@nofun.com", "geheim123"));
        getItemList().add(new Benutzer(PersonAnrede.MRS, "Klara", "Fall", "klara@fall.de", "unklar"));
    }

    public Benutzer getItem() {
        return item;
    }

    public void setItem(Benutzer item) {
        this.item = item;
    }

    public void resetItem() {
        item = new Benutzer();
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

    public void removeItem(Benutzer b) {
        if (b != null && getItemList().contains(b)) {
            getItemList().remove(b);
        }
    }


    //-------------------------------------------------------------------------
    //  Else
    //-------------------------------------------------------------------------
    public List getAnredeOptionen() {
        return Arrays.asList(PersonAnrede.values());
    }


}
