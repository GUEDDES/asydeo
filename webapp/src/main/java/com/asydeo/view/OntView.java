package com.asydeo.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import com.asydeo.ontology.Asydeo;


public class OntView {
	
	OntResource i;
	Individual subject;
	
	public OntView(OntResource i) {
		this.i = i;
	}
	
	public OntView(OntResource i, Individual subject) {
		this.i = i;
		this.subject = subject;
	}
	
	public String getLabel() {
		return i.getLabel(null);
	}
	
	public String getURI() {
		if (i.isAnon())
			return "anon";
		else
			return i.getModel().shortForm(i.getURI());
	}

	public String getComment() {
		return i.getComment(null);
	}
	
	public String getType() {
		String longuri = i.getRDFType(true).getURI();
		return i.getModel().shortForm(longuri);
	}

	// Get the label of this individual's class. Since calling
	// the Individual.getOntClass() cannot guarantee which class
	// may be returned, we extract the name from the individual's URI
	public String getClassLabel() {
	    String label = getType();

	    String regex = Asydeo.PREFIX + ":\\w+";
	    Matcher m = Pattern.compile(regex).matcher( getURI() );

	    if ( m.find() ) {
	        String classUri = getURI().substring(0, m.end());
	        classUri = classUri.replace(Asydeo.PREFIX + ":", "");
	        classUri = Asydeo.NS + classUri;
	        
	        OntResource resource = i.getOntModel().getOntResource(classUri);

	        if ( resource != null ) {
	            label = resource.getLabel(null);
	        }
	    }
	    
	    return label;
	}
	
	public static OntView $(OntResource item) {
		return new OntView(item);
	}
	
	public static OntView $(OntResource item, Individual subject) {
		return new OntView(item, subject);
	}
	
	public Collection<OntView> getItems() {
		OntProperty p = (OntProperty)i;
		NodeIterator it = subject.listPropertyValues(p);
		ArrayList<OntView> result = new ArrayList<OntView>();
		while(it.hasNext()) {
			OntResource r = (OntResource)it.next();
			result.add(OntView.$(r));
		}
		return result;
	}

}
