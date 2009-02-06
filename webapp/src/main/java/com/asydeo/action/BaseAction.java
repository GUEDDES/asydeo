package com.asydeo.action;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.vocabulary.OWL;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;



public class BaseAction implements ActionBean {
	protected ASDContext context;
	
	public ASDContext getContext() {
		return context;
	}

	public void setContext(ActionBeanContext c) {
		context = (ASDContext)c;
	}

	public OntModel m() {
		return context.getModel();
	}

	public OntClass owlThing() {
		return m().getOntClass(OWL.Thing.getURI());
	}
	
	public OntClass rootClass() {
		return m().getOntClass("http://asydeo.com/schema#ConfigurableItem");
	}
	
}
