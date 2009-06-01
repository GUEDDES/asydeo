package com.asydeo.action;

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.model.StatementBean;
import static com.asydeo.util.AsydeoConfig.*;
import com.asydeo.view.OntView;
import com.asydeo.view.View;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.shared.Lock;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;

@UrlBinding("/asset/edit")
public class EditAction extends BaseAction {

	String uri;
	String classUri;
	StatementBean bean;

	public EditAction() {
		super();
	}

	@DefaultHandler
	public Resolution start() {
		if (classUri == null || classUri.equals("asydeo:ConfigurableItem")) {
			Individual i = individual(uri);
			classUri = i.getRDFType(true).getURI();
			classUri = m().shortForm(classUri);
		}
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
		return home();
	}

	@HandlesEvent("cancel")
	public Resolution home() {
		return new RedirectResolution(ListAction.class).addParameter("uri",
				classUri);
	}

	@HandlesEvent("unrelate")
	public Resolution unrelate() {
		try {
			m().enterCriticalSection(Lock.WRITE);
			Resource r = individual(bean.getS());
			Property p = ontProperty(bean.getV());
			Resource n = individual(bean.getO());
			m().remove(r, p, n);
		} finally {
			m().leaveCriticalSection();
		}
		return null;
	}

	public View[] getViews() {
		return context.getViews(individual(uri), ontClass(classUri));
	}

	public Collection<OntView> getObjectProperties() {
		try {
			m().enterCriticalSection(Lock.READ);
			final Individual i = individual(uri);
			return new each(chooseNonFunctional()) {
				void $() {add(i);}}.result;
		} finally {
			m().leaveCriticalSection();
		}
	}

	public Collection<OntView> getFunctionalProperties() {
		try {
			m().enterCriticalSection(Lock.READ);

			final Individual i = individual(uri);
			return new each(chooseFunctional()) {
				void $() {add(i);}}.result;
		} finally {
			m().leaveCriticalSection();
		}
	}

	public Collection<OntView> getReferences() {
		try {
			m().enterCriticalSection(Lock.READ);

			final Individual i = individual(uri);
			Property p = m().getOntProperty(OWL.ObjectProperty.getURI());
			StmtIterator it = m().listStatements(null, null, i);
			ArrayList<OntView> results = new ArrayList<OntView>();
			while(it.hasNext()) {
				Statement s = it.nextStatement();
				if (s.getPredicate().getURI().startsWith( getAsydeoNS() )) {
					Individual j = (Individual)s.getSubject().as(Individual.class);
					assert(j!=null);
					results.add(OntView.$(j));
				}
			}
			return results;
		} finally {
			m().leaveCriticalSection();
		}
	}	
	
	private ExtendedIterator chooseNonFunctional() {
		return ontClass(classUri).listDeclaredProperties().filterKeep(
				Filters.nonfunctional);
	}

	private ExtendedIterator chooseFunctional() {
		return ontClass(classUri).listDeclaredProperties().filterKeep(
				Filters.functional);
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
