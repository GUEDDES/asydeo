package com.asydeo.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.view.OntView;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

@UrlBinding("/asset/classes")
public class ListClassesAction extends BaseAction {
	
	@DefaultHandler
	public Resolution start() {			
		return new ForwardResolution("/listclasses.jsp");
	}
	
	public List<OntView> getList() {
		ArrayList<OntView> classes = new ArrayList<OntView>();
		ExtendedIterator it = rootClass().listSubClasses(true);
		while (it.hasNext()) {
			classes.add(new OntView((OntClass) it.next()));
		}
		return classes;
	}
}
