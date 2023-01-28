package com.wolvtech.utils.messages;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesMessages implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private void add(String msg, FacesMessage.Severity severity) {
		FacesMessage facesMessage = new FacesMessage(msg);
		facesMessage.setSeverity(severity);
		
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}
	
	public void info(String msg) {
		add(msg, FacesMessage.SEVERITY_INFO);
	}
	
	public void error(String msg) {
		add(msg, FacesMessage.SEVERITY_ERROR);
	}
	
	public void warning(String msg) {
		add(msg, FacesMessage.SEVERITY_WARN);
	}
	
}
