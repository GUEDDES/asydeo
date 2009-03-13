package com.asydeo.view;

import java.util.ArrayList;
import java.util.Collection;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;

public class OntView {
	
	OntResource i;
	Individual subject;
	
	public OntView(OntResource i) {
		this.i = i;
	}
	
	public OntView(OntResource i, Individual subject) {
		this.i = i;
		this.subject = subject;
	}
	
	public String getLabel() {
		return i.getLabel(null);
	}
	
	public String getURI() {
		return i.getModel().shortForm(i.getURI());
	}

	public String getComment() {
		return i.getComment(null);
	}

	public static OntView $(OntResource item) {
		return new OntView(item);
	}
	
	public static OntView $(OntResource item, Individual subject) {
		return new OntView(item, subject);
	}
	
	public Collection<OntView> getItems() {
		OntProperty p = (OntProperty)i;
		NodeIterator it = subject.listPropertyValues(p);
		ArrayList<OntView> result = new ArrayList<OntView>();
		while(it.hasNext()) {
			OntResource r = (OntResource)it.next();
			result.add(OntView.$(r));
		}
		return result;
	}

}
