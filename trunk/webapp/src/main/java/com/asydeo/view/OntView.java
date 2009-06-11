package com.asydeo.view;

import static com.asydeo.util.AsydeoConfig.getAsydeoNS;
import static com.asydeo.util.AsydeoConfig.getAsydeoPrefix;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.asydeo.util.AsydeoConfig;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.OntResource;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;


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
	
	public String getImage() {
		if (i != null) {
			Property p = i.getModel().getProperty(AsydeoConfig.getAsydeoNS() + "image");
			RDFNode value =  i.getPropertyValue(p);
			if (value != null) {
				Literal l  = (Literal)value.as(Literal.class);
				return l.getString();
			}
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
		Resource r = i.getRDFType(true);
		if (r==null)
			return "";
		String longuri = i.getRDFType(true).getURI();
		return i.getModel().shortForm(longuri);
	}

	// Get the label of this individual's class. Since calling
	// the Individual.getOntClass() cannot guarantee which class
	// may be returned, we extract the name from the individual's URI
	public String getClassLabel() {
	    String label = getType().replace(":", "");

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
			assert(r!=null);
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
