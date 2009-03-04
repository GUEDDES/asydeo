package com.asydeo.view;

import java.util.ArrayList;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class Util {
	
	public static View[] getView(Individual i) {
		Property editor = i.getModel().createProperty("http://asydeo.com/schema#editor");
		OntClass ontClass = i.getOntClass();
		ExtendedIterator it = ontClass.listDeclaredProperties();
		ArrayList<View> views = new ArrayList<View>();
		while(it.hasNext())  {
			View v = render(editor, (OntProperty)it.next());
			if (v!=null) {
				v.setIndividual(i);
				views.add(v);
			}
		}
		return views.toArray(new View[0]);
	}
	
	public static View[] getView(OntModel m, String classUri) {
		Property editor = m.createProperty("http://asydeo.com/schema#editor");
		String uri = m.expandPrefix(classUri);
		OntClass ontClass = m.getOntClass(uri);
		ExtendedIterator it = ontClass.listDeclaredProperties();
		ArrayList<View> views = new ArrayList<View>();
		while(it.hasNext())  {
			View v = render(editor, (OntProperty)it.next());
			if (v!=null)
				views.add(v);
		}
		return views.toArray(new View[0]);
	}

	public static View render(Property editor, OntProperty p) {
		RDFNode node = p.getPropertyValue(editor);
		if (node == null)
			return null;
		Individual iview = (Individual)node.as(Individual.class);
		OntClass c = iview.getOntClass();
		try {
			Class cls = Class.forName("com.asydeo.view." + c.getLocalName());
			View view = (View)cls.newInstance();
			view.setOntProperty(p);
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
