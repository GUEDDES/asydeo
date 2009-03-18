package com.asydeo.view;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class TextField extends Text {

	@Override
	public String getContent(Individual i) {
		String value = "";
		if (i!=null) {
			RDFNode node  = i.getPropertyValue(p);
			if (node != null)
				value = ((Literal)node.as(Literal.class)).getString();
		}
		ResourceBundle b = ResourceBundle.getBundle("StripesResources");
		String format = b.getString("textfield");
		return MessageFormat.format(format, p.getLocalName(), value,p.getLabel(null));	
	}

}