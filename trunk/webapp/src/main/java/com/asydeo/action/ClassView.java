package com.asydeo.action;

import com.hp.hpl.jena.ontology.OntClass;

public class ClassView {
	OntClass cls;
	
	public ClassView(OntClass cls) {
		this.cls = cls;
	}
	
	public String getLabel() {
		return cls.getLabel(null);
	}
	
	public String getURI() {
		return cls.getURI();
	}

}
