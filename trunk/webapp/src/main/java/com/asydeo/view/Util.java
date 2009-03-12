package com.asydeo.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class Util {
	
	public static View[] getView(Individual i) {
		OntClass ontClass = i.getOntClass();
		ExtendedIterator it = ontClass.listDeclaredProperties();
		ArrayList<View> views = new ArrayList<View>();
		while(it.hasNext())  {
			View v = render((OntProperty)it.next());
			if (v!=null) {
				v.setIndividual(i);
				views.add(v);
			}
		}
		sort(views);
		return views.toArray(new View[0]);
	}
	
	public static View[] getView(OntModel m, String classUri) {
		
		String uri = m.expandPrefix(classUri);
		OntClass ontClass = m.getOntClass(uri);
		ExtendedIterator it = ontClass.listDeclaredProperties();
		ArrayList<View> views = new ArrayList<View>();
		while(it.hasNext())  {
			View v = render((OntProperty)it.next());
			if (v!=null)
				views.add(v);
		}
		sort(views);
		return views.toArray(new View[0]);
	}

	private static void sort(ArrayList<View> views) {
		Collections.sort(views, new Comparator<View>() {

			public int compare(View o1, View o2) {
				if ( o1.getOrder() > o2.getOrder()) {
					return -1;
				} else if (o1.getOrder() < o2.getOrder()) {
					return 1;
				} else 
					return 0;
			}
			
		});
	}

	private static View render(OntProperty p) {
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
