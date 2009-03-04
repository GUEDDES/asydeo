package com.asydeo.action;

import java.util.ArrayList;
import java.util.Collection;

import com.asydeo.view.OntView;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public abstract class each {

	OntResource item;
	ArrayList<OntView> result = new ArrayList<OntView>();
	
	public each(ExtendedIterator it) {
		while(it.hasNext()) {
			item = (OntResource)it.next();
			$();
		}
	}
	
	protected void add(OntView view) {
		result.add(view);
	}
	

	public each(OntClass cls) {
		this(cls.listInstances());
	}
	
	public Collection<OntView> result() {
		return result;
	}
	
	abstract void $();

}
