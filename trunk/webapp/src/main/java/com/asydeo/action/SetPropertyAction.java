package com.asydeo.action;

import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.model.AddRelation;
import com.asydeo.view.OntView;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;

@UrlBinding("/asset/setproperty")
public class SetPropertyAction extends BaseAction {

	Individual subject;
	OntProperty verb;
	AddRelation bean;
	
	@DefaultHandler
	public Resolution start() {
		subject = individual(bean.getS());
		verb = rawOntProperty(bean.getV());
		return new ForwardResolution("/setproperty.jsp");
	}

	@HandlesEvent("set")
	public Resolution add() {
		subject = individual(bean.getS());
		verb = rawOntProperty(bean.getV());
		for (String uri : bean.getO()) {
			Individual object = individual(uri);
			subject.setPropertyValue(verb, object);
		}
		return resolve();
	}

	@HandlesEvent("cancel")
	public Resolution cancel() {
		return resolve();
	}
	
	private Resolution resolve() {
		RedirectResolution r = new RedirectResolution(EditAction.class);
		r.addParameter("uri", bean.getS());
		r.addParameter("classUri", bean.getClassUri());
		return r;
	}
	


	public Collection<OntView> getCandidates() {
		OntResource r = verb.getRange();
		r = m().getOntClass(r.getURI());
		return new each(r.asClass().listInstances()) {
			void $() {if (!m().contains(subject,verb,item))
					add(OntView.$(item));}}.result;
	}

	public OntView getVerb() {
		return OntView.$(verb);
	}

	public AddRelation getBean() {
		return bean;
	}

	public void setBean(AddRelation bean) {
		this.bean = bean;
	}

	public OntView getSubject() {
		return OntView.$(subject);
	}

}
