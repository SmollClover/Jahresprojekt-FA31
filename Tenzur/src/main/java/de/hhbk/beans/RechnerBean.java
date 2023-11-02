package de.hhbk.beans;
  
import de.hhbk.model.Rechner;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named(value = "computer") 
@SessionScoped
public class RechnerBean extends BeanTemplate 
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public RechnerBean() { super(); } 

    @PostConstruct
    public void init()
    { 
        resetItem();
        getItemList().add(new Rechner("112233", "Fujitsu", "ESPRIMO P757/E85+")); 
        getItemList().add(new Rechner("892211", "Fujitsu", "Esprimo Q7010")); 
        getItemList().add(new Rechner("121212", "HP", "EliteDesk 705 G8 MT"));
    }
 

  //-------------------------------------------------------------------------
  //  Item
  //-------------------------------------------------------------------------     
    protected Rechner item = null; 
    
    public Rechner getItem() { return item; }

    public void setItem(Rechner item) { this.item = item; } 
    
    public void resetItem() { item = new Rechner();; }     


  //-------------------------------------------------------------------------
  //  List
  //-------------------------------------------------------------------------     
    protected List<Rechner> itemList = null;
    
    public List getItemList()
    {
        if (itemList == null) { itemList = new ArrayList<>(); }
        return itemList;
    }

    public void setItemList(List itemList)
    {
        this.itemList = itemList;
    }
    
    public void saveItem()
    {
        if (item != null && item.hasId() && !getItemList().contains(item)) { getItemList().add(item); }
    }
    
    public void removeItem(Rechner r)
    {
        if (r != null && getItemList().contains(r))
        {
            getItemList().remove(r);
        }
    }
 


}
