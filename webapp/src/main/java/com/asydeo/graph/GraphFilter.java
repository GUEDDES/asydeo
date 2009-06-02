package com.asydeo.graph;

import java.util.HashSet;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;

public class GraphFilter {

	Model model;
	HashSet<OntClass> visible;
	
	public void GraphFilter() {
	}

	public void addVisible(OntClass c) {
		visible.add(c);
	}
	
	public Model filter(Individual n) {
		model = ModelFactory.createDefaultModel();
		return filter(n, n.listProperties());		
	}
	
	public Model filter(Individual n, StmtIterator statements) {
		
		while(statements.hasNext()) {
			Statement s = statements.nextStatement();
			if (visible(s)) {
				
			}
		}
		return null;
	}

	private boolean visible(Statement s) {
		RDFNode node = s.getObject();
		if (node.canAs(Individual.class)) {
			Individual i = (Individual)node.as(Individual.class);
			for (OntClass cls : visible)
				if (i.hasRDFType(cls))
					return true;
		}
		return false;
	}
	
}
