package com.asydeo.view;

import com.hp.hpl.jena.ontology.OntResource;

public class OntView {
	
	OntResource i;
	
	public OntView(OntResource i) {
		this.i = i;
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

}
