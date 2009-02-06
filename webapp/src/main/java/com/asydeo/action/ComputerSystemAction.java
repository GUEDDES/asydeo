package com.asydeo.action;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

@UrlBinding("/asset/new")
public class ComputerSystemAction extends BaseAction {
	
	@DefaultHandler
	public Resolution start() {
		return new ForwardResolution("/new.jsp");
	}
	
	public List<ClassView> getClasses() {
		ArrayList<ClassView> classes = new ArrayList<ClassView>();
		ExtendedIterator it = rootClass().listSubClasses(true);
		while(it.hasNext()) {
			classes.add(new ClassView((OntClass)it.next()));
		}
		return classes;
	}

}
