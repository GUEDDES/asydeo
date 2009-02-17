package com.asydeo.view;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Literal;

public class TextArea extends Text {

	@Override
	public String getContent(Individual i) { 
		String value = "";
		if (i!=null) {
			Literal l = (Literal) i.getPropertyValue(p).as(Literal.class);
			value = l.getString();
		}
		ResourceBundle b = ResourceBundle.getBundle("StripesResources");
		String format = b.getString("textarea");
		return MessageFormat.format(format, p.getLocalName(), value, p.getLabel(null));	
	}

}
