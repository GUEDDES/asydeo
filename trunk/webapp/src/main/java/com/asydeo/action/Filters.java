package com.asydeo.action;

import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.Filter;

public class Filters {
	public static Filter nonfunctional = new Filter() {
		public boolean accept(Object o) {
			if ( o instanceof OntProperty) {
				OntProperty op = (OntProperty)o;
				return !op.isFunctionalProperty() && op.isObjectProperty();
			}
			return false;
		}		
	};
}
