package com.asydeo.view;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class CommaSeperatedList extends List {


	public String getContent(Individual i) {
		StringBuilder value = new StringBuilder();
		if (i!=null) {
			NodeIterator it = i.listPropertyValues(p);
			while(it.hasNext()) {
				RDFNode n = it.nextNode();
				Literal l = (Literal)n.as(Literal.class);
				value.append("," + l.getString());
			}
			
		}
		ResourceBundle b = ResourceBundle.getBundle("StripesResources");
		String format = b.getString("textfield");
		String show = "";
		if (value.length() > 0)
			show = value.substring(1);
		return MessageFormat.format(format, p.getLocalName(), show, p.getLabel(null));	
	}
	
	public void apply(HttpServletRequest r) {		
		String value = r.getParameter(p.getLocalName());
		apply(value);
	}

	public void apply(String value) {
		i.removeAll(p);
		Literal l  = p.getModel().createTypedLiteral(value);
		String[] values = l.getString().split(",");
		for (String s : values) {
			l  = p.getModel().createTypedLiteral(s.trim());
			i.addProperty(p, l);					
		}
	}


}
