package com.asydeo.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

import com.asydeo.domain.AsydeoSession;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.vocabulary.OWL;


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

	public OntModel raw() {
		return context.getRawModel();
	}

	public OntClass owlThing() {
		return m().getOntClass(OWL.Thing.getURI());
	}
	
	public OntClass ontClass(String uri) {
		String longuri = m().expandPrefix(uri);
		return m().getOntClass(longuri);
	}
	
	public Individual individual(String uri) {
		String longuri = m().expandPrefix(uri);
		return m().getIndividual(longuri);		
	}

	public OntProperty ontProperty(String uri) {
		String longuri = m().expandPrefix(uri);
		return m().getOntProperty(longuri);
	}

	public OntProperty rawOntProperty(String uri) {
		String longuri = raw().expandPrefix(uri);
		return raw().getOntProperty(longuri);
	}

	public OntClass rootClass() {
		return m().getOntClass("http://asydeo.com/schema#ConfigurableItem");
	}
	
	public OntClass visibleClass() {
		return m().getOntClass("http://asydeo.com/schema#VisibleClass");
	}
	
	public String shortUri(String uri) {
	    if ( uri.contains(Asydeo.NS) ) {
	        return uri.substring(Asydeo.NS.length(), uri.length());
	    }
	    return uri;
	}
	
	public String label(OntResource r) {
	    String label;
	    
	    label = r.getLabel( context.getSession().getLang() );
	    
	    if ( label == null || label.isEmpty() ) {
	        label = r.getLabel(null);
	    }
	    
	    return label;
	}
	
	public AsydeoSession getUserSession() {
		return context.getSession();
	}
	
	public String getModelName() {
		return context.getSession().getModelName();
	}

	public void setModelName(String s) {
		context.getSession().setModelName(s);
	}
}
