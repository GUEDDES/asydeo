package com.asydeo.action;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public abstract class each {

	Individual item;
	public each(ExtendedIterator it) {
		while(it.hasNext()) {
			item = (Individual)it.next();
			$();
		}
	}
	
	public each(OntClass cls) {
		this(cls.listInstances());
	}
	
	abstract void $();

}
