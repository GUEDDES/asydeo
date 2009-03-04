package com.asydeo.action;

import net.sourceforge.stripes.action.ActionBeanContext;

import com.hp.hpl.jena.ontology.OntModel;


public class ASDContext extends ActionBeanContext {

	public OntModel getModel() {
		return (OntModel)getServletContext().getAttribute("model");
	}

	public OntModel getRawModel() {
		return (OntModel)getServletContext().getAttribute("rawmodel");
	}
}
