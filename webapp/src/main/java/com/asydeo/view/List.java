package com.asydeo.view;

import javax.servlet.http.HttpServletRequest;

import com.hp.hpl.jena.rdf.model.Literal;

public abstract class List extends BasicView {

	public void apply(HttpServletRequest r) {
		String[] values = r.getParameterValues(p.getLocalName());		
		for (String value : values) {
			Literal l  = p.getModel().createTypedLiteral(value);
			i.addProperty(p, l);					
		}
	}


}
