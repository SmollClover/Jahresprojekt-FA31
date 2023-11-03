package de.hhbk.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public abstract class BeanTemplate implements Serializable {
    public BeanTemplate() {
        super();
    }

<<<<<<< HEAD
    protected void setMessage(String comonentId, FacesMessage.Severity type, String header, String msg) {
        FacesContext.getCurrentInstance().addMessage(comonentId, new FacesMessage(type, header, msg));
=======
public abstract class BeanTemplate implements Serializable {
    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------
    public BeanTemplate() {
        super();
>>>>>>> origin/implement-navigation
    }

<<<<<<< HEAD
    protected void setErrorMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_ERROR, header, msg);
    }
=======
>>>>>>> origin/implement-navigation

<<<<<<< HEAD
    protected void setInfoMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_INFO, header, msg);
    }
=======
    //-------------------------------------------------------------------------
    //  Else ...
    //-------------------------------------------------------------------------
    protected void setMessage(String comonentId, FacesMessage.Severity type, String header, String msg) {
        FacesContext.getCurrentInstance().addMessage(comonentId, new FacesMessage(type, header, msg));
    }

    protected void setErrorMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_ERROR, header, msg);
    }

    protected void setInfoMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_INFO, header, msg);
    }


>>>>>>> origin/implement-navigation
}
