package com.asydeo.view;

import javax.servlet.http.HttpServletRequest;

import com.hp.hpl.jena.rdf.model.Literal;

public abstract class Text extends BasicView {


	@Override
	public void apply(HttpServletRequest r) {
		String value = r.getParameter(p.getLocalName());
		Literal l  = p.getModel().createTypedLiteral(value);
		i.setPropertyValue(p, l);		
	}




}
