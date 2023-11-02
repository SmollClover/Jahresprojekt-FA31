package de.hhbk.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public abstract class BeanTemplate implements Serializable
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public BeanTemplate() 
    { 
        super();  
    } 

 
  //-------------------------------------------------------------------------
  //  Else ... 
  //-------------------------------------------------------------------------     
    protected void setMessage(String comonentId, FacesMessage.Severity type, String header, String msg) { FacesContext.getCurrentInstance().addMessage(comonentId, new FacesMessage(type, header, msg)); }    
    
    protected void setErrorMessage(String header, String msg) { setMessage(null, FacesMessage.SEVERITY_ERROR, header, msg); }
    
    protected void setInfoMessage(String header, String msg) { setMessage(null, FacesMessage.SEVERITY_INFO, header, msg); }
    

    
}