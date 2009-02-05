package com.asydeo.action;

import com.hp.hpl.jena.ontology.OntModel;

import net.sourceforge.stripes.action.ActionBeanContext;


public class ASDContext extends ActionBeanContext {

	public OntModel getModel() {
		return (OntModel)getServletContext().getAttribute("model");
	}

	public void login(String string) {
		this.getRequest().getSession().setAttribute("login", string);
	}	

}
