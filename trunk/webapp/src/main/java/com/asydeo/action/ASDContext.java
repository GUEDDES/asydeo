package com.asydeo.action;

import java.security.Principal;

import thewebsemantic.binding.Jenabean;

import net.sourceforge.stripes.action.ActionBeanContext;

import com.asydeo.domain.User;
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
	
	public User getUser() {
		Principal p = getRequest().getUserPrincipal();
		if ( p == null)
			return null;
		return Jenabean.load(User.class, p.getName());
	}
}
