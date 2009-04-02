package com.asydeo.view;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;

public class ViewFactory {
	public static View create(OntProperty p) {
		OntModel m = p.getOntModel();
		Property editor = m.createProperty("http://asydeo.com/schema#editor");
		Property order = m.createProperty("http://asydeo.com/schema#order");
		RDFNode node = p.getPropertyValue(editor);
		RDFNode tmp = p.getPropertyValue(order);
		int precedence = 0;
		if (tmp != null) {
			Literal l = (Literal)tmp.as(Literal.class);
			precedence = l.getInt();
		}
		if (node == null)
			return null;
		Individual iview = (Individual)node.as(Individual.class);
		OntClass c = iview.getOntClass();
		try {
			Class cls = Class.forName("com.asydeo.view." + c.getLocalName());
			View view = (View)cls.newInstance();
			view.setOntProperty(p);
			view.setOrder(precedence);
			return view;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
