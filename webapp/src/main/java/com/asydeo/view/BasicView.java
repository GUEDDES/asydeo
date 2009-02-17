package com.asydeo.view;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;

public abstract class BasicView implements View {

	protected OntProperty p;
	protected Individual i;
	
	public String getContent() {
		return getContent(i);
	}
	
	public void setOntProperty(OntProperty p) {
		this.p = p;
	}
	
	public void setIndividual(Individual i) {
		this.i = i;
	}
	

}
