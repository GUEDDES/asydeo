package com.asydeo.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import static com.asydeo.util.AsydeoConfig.*;
import com.asydeo.view.View;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.shared.PropertyNotFoundException;

@UrlBinding("/asset/new")
public class NewAction extends BaseAction {

	String classUri;
	String name;

	@DefaultHandler
	public Resolution start() {
		return new ForwardResolution("/new.jsp");
	}
	
	@HandlesEvent("cancel") 
	public Resolution cancel() {
		return new RedirectResolution(ListAction.class).addParameter("uri", classUri);
	}

	@HandlesEvent("create")
	public Resolution create() {
		try {
			m().enterCriticalSection(Lock.WRITE);
			OntClass ontClass = ontClass(classUri);
			int id = getNextId(ontClass);			
			Individual newi = ontClass.createIndividual(getAsydeoNS() + ontClass.getLocalName() + ':' + id);
			newi.setLabel(name, null);
			for (View v : context.getViews(newi, ontClass))
				v.apply(context.getRequest());			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			m().leaveCriticalSection();
		}
		return new RedirectResolution(ListAction.class).
			addParameter("uri", classUri);

	}

	private int getNextId(OntClass cls) {
		Property p = m().createProperty(getAsydeoNS() + "sequence");
		int i=0;
		try {			
			Statement stmt = cls.getRequiredProperty(p);
			i = stmt.getInt();
		} catch (PropertyNotFoundException e) {
			cls.setPropertyValue(p, m().createTypedLiteral(0));
		} 
		cls.setPropertyValue(p, m().createTypedLiteral(i+1));
		return i;
	}

	public View[] getViews() {
		return context.getViews(classUri);
	}

	public String getClassUri() {
		return classUri;
	}

	public void setClassUri(String classUri) {
		this.classUri = classUri;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
