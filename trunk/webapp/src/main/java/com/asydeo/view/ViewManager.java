package com.asydeo.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class ViewManager {
	
	public View[] getView(OntModel raw, Individual i, OntClass ontClass) {
		//OntClass ontClass = i.getOntClass();
		ExtendedIterator it = ontClass.listDeclaredProperties();
		ArrayList<View> views = new ArrayList<View>();
		while(it.hasNext())  {
			OntProperty p = (OntProperty)it.next();
			
			//important: we don't want an inferencing
			//model for the range, we want just the raw
			//declared range
			p = raw.getOntProperty(p.getURI());
			if (p==null)
				continue;
			View v = ViewFactory.create(p);
			if (v!=null) {
				v.setIndividual(i);
				views.add(v);
			}
		}
		sort(views);
		return views.toArray(new View[0]);
	}
	
	public View[] getView(OntModel m, String classUri) {
		
		String uri = m.expandPrefix(classUri);
		OntClass ontClass = m.getOntClass(uri);
		ExtendedIterator it = ontClass.listDeclaredProperties();
		ArrayList<View> views = new ArrayList<View>();
		while(it.hasNext())  {
			View v = ViewFactory.create((OntProperty)it.next());
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

}
