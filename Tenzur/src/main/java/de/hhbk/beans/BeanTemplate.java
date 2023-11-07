package de.hhbk.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

public abstract class BeanTemplate implements Serializable {
    public BeanTemplate() {
        super();
    }

    protected void setMessage(String comonentId, FacesMessage.Severity type, String header, String msg) {
        FacesContext.getCurrentInstance().addMessage(comonentId, new FacesMessage(type, header, msg));
    }

    protected void setErrorMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_ERROR, header, msg);
    }

    protected void setInfoMessage(String header, String msg) {
        setMessage(null, FacesMessage.SEVERITY_INFO, header, msg);
    }
}
