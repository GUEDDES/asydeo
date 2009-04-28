package com.asydeo.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

import com.asydeo.ontology.Asydeo;
import static com.asydeo.util.AsydeoConfig.*;


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
	    if ( i != null ) {
	        return i.getLabel(null);
	    }
	    return null;
	}
	
	public String getURI() {
	    if ( i == null )
	        return null;
	    else if (i.isAnon())
			return "anon";
		else
			return i.getModel().shortForm(i.getURI());
	}

	public String getComment() {
		return i.getComment(null);
	}

	public String getDescription() {
	    return getProperty("description");
	}
	
	public String getOrganization() {
        return getProperty("hasOrganization");
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

	    String regex = getAsydeoPrefix() + ":\\w+";
	    Matcher m = Pattern.compile(regex).matcher( getURI() );

	    if ( m.find() ) {
	        String classUri = getURI().substring(0, m.end());
	        
	        // If a class is loaded into the model without being defined
	        // in the ontology, get the name from the URI
	        // TODO: This doesn't work
	        if ( label.isEmpty() ) {
	            label = classUri;
	        }
	        
	        classUri = classUri.replace(getAsydeoPrefix() + ":", "");
	        classUri = getAsydeoNS() + classUri;
	        
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

	// Get the text value of a property, whether a literal or a resource
	private String getProperty(String property) {
	    String name = "";
	    OntProperty p =
          i.getOntModel().getOntProperty(getAsydeoNS() + property);
	    
	    if ( p != null && i.hasProperty(p) ) {
	        RDFNode node = i.getPropertyValue(p);
	        
	        if ( node.isLiteral() ) {
	            name = node.toString();
	        }
	        else if ( node.isResource() ) {
	            OntResource r = (OntResource)node;
	            name = r.getLabel(null);
	        }
	    }
	    
	    return name;
	}
}
