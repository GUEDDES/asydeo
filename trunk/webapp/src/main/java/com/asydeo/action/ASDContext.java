package com.asydeo.action;

import net.sourceforge.stripes.action.ActionBeanContext;

import com.asydeo.view.Util;
import com.asydeo.view.View;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;


public class ASDContext extends ActionBeanContext {

	public OntModel getModel() {
		return (OntModel)getServletContext().getAttribute("model");
	}

	public OntModel getRawModel() {
		return (OntModel)getServletContext().getAttribute("rawmodel");
	}
	
	public View[] getViews(Individual i) {
		return Util.getView(i);
	}
}
