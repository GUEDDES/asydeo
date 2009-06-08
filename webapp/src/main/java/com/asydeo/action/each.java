package com.asydeo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.asydeo.view.OntView;
import com.asydeo.view.OntViewComparator;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
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
	
	public boolean functional() {
		if ( item.isObjectProperty() ) {
			OntProperty p = (OntProperty)item;
			return p.isFunctionalProperty();
		}
		return false;
	}
	
	abstract void $();
	
	void add(Individual i) {
		result.add(OntView.$(item, i));
	}
	
	public Collection<OntView> sorted() {
		Collections.sort(result, new OntViewComparator());	
		return result;
	}

}
