package com.asydeo.action;

import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.util.iterator.Filter;

public class Filters {
	public static Filter nonfunctional = new Filter() {
		public boolean accept(Object o) {
			if (o instanceof OntProperty) {
				OntProperty op = (OntProperty)o;
				int subs = op.listSubProperties(true).toList().size();
				return !op.isFunctionalProperty() && op.isObjectProperty() && subs == 0;
			}
			return false;
		}		
	};
	
	public static Filter functional = new Filter() {
		public boolean accept(Object o) {
			if (o instanceof OntProperty) {
				OntProperty op = (OntProperty)o;
				return op.isFunctionalProperty() && op.isObjectProperty();
			}
			return false;
		}		
	};
	
	public static Filter allow = new Filter() {
		public boolean accept(Object o) {
			if (o instanceof OntProperty) {
				OntProperty op = (OntProperty)o;				
				return op.isObjectProperty();
				
			}
			return false;
		}		
	};
}
