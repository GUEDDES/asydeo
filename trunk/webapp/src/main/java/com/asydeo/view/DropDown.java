package com.asydeo.view;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class DropDown extends BasicView {

	public String getContent(Individual i) {
		StringBuilder options = new StringBuilder();
		RDFNode value = null;
		OntModel m = p.getOntModel();
		if (i != null) {
			value = i.getPropertyValue(p);
			m = i.getOntModel();
		}
		OntResource r = p.getRange();
		r = m.getOntResource(r);
		ExtendedIterator it = r.asClass().listInstances();
		while (it.hasNext()) {
			Individual candidate = (Individual) it.next();
			String selected = "";
			if (candidate.equals(value))
				selected = "selected=\"selected\"";
			options.append("<option " + selected + " value=\""
					+ candidate.getURI() + "\">" + candidate.getLabel(null)
					+ "</option>");
		}

		ResourceBundle b = ResourceBundle.getBundle("StripesResources");
		String format = b.getString("dropdown");

		return MessageFormat.format(format, p.getLocalName(), options, p
				.getLabel(null));
	}

	@Override
	public void apply(HttpServletRequest req) {
		String value = req.getParameter(p.getLocalName());
		Resource r = p.getModel().createResource(value);
		i.removeAll(p);
		i.setPropertyValue(p, r);
	}
}
