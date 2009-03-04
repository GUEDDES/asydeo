package com.asydeo.action;

import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.model.StatementBean;
import com.asydeo.view.OntView;
import com.asydeo.view.Util;
import com.asydeo.view.View;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.shared.Lock;

@UrlBinding("/asset/edit")
public class EditAction extends BaseAction {
	
	String uri;
	String classUri;
	StatementBean bean;
	
	@DefaultHandler
	public Resolution start() {			
		return new ForwardResolution("/edit.jsp");
	}
	
	@HandlesEvent("update")
	public Resolution update() {
		try {
			m().enterCriticalSection(Lock.WRITE);
			for (View view : getViews())
				view.apply(context.getRequest());
		} finally {
			m().leaveCriticalSection();
		}
		return new RedirectResolution(ListAction.class).
			addParameter("uri", classUri);
	}
	
	@HandlesEvent("unrelate")
	public Resolution unrelate() {
		try {
			
			m().enterCriticalSection(Lock.WRITE);
			Resource r = individual(bean.getS());
			Property p = ontProperty(bean.getV());
			Resource n = individual(bean.getO());
			System.out.println(r + " " + p + " " + n);
			m().remove(r,p,n);	
		} finally {
			m().leaveCriticalSection();
		}		
		return new ForwardResolution("/edit.jsp");
	}
	
	public View[] getViews() {
		return Util.getView(individual(uri));
	}
	
	public Collection<OntView> getObjectProperties() {
		final Individual i = individual(uri);
		return new each(ontClass(classUri).listDeclaredProperties() ) {
			void $() { if (item.isObjectProperty())
					result.add(OntView.$(item, i));}}.result;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getClassUri() {
		return classUri;
	}

	public void setClassUri(String classUri) {
		this.classUri = classUri;
	}

	public StatementBean getBean() {
		return bean;
	}

	public void setBean(StatementBean bean) {
		this.bean = bean;
	}

}
