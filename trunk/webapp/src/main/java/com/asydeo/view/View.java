package com.asydeo.view;

import javax.servlet.http.HttpServletRequest;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;

public interface View {

	public String getContent();
	public String getContent(Individual i);
	public void apply(HttpServletRequest r);
	public void setOntProperty(OntProperty p);
	public void setIndividual(Individual i);
}
