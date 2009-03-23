package com.asydeo.view;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class Checkbox extends BasicView {

	
	@Override
	public void apply(HttpServletRequest r) {
		String value = r.getParameter(p.getLocalName());
		boolean b = false;
		if ( value != null && value.equals("on"))
			b = true;
		Literal l  = p.getModel().createTypedLiteral(b);
		i.setPropertyValue(p, l);		
	}
	
	public String getContent(Individual i) {
		String value = "";
		if (i!=null) {
			RDFNode node  = i.getPropertyValue(p);
			if (node != null) {
				if (((Literal)node.as(Literal.class)).getBoolean()) {
					value = "checked=checked";
				}
			}
		}
		ResourceBundle b = ResourceBundle.getBundle("StripesResources");
		String format = b.getString("checkbox");
		
		return MessageFormat.format(format, p.getLocalName(), value,p.getLabel(null));	
	}
	
}
