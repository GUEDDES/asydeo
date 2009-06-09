package com.asydeo.graph;

import java.util.HashSet;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

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
				model.add(n, s.getPredicate(), s.getObject());
				Individual i = (Individual)s.getObject().as(Individual.class);
				filter(i, i.listProperties());
			} else if (individual(s)){
				Individual i = (Individual)s.getObject().as(Individual.class);
				filter(n, i.listProperties());
			}
		}
		return null;
	}

	private boolean individual(Statement s) {
		RDFNode node = s.getObject();
		return (node.canAs(Individual.class));
	}

	private boolean visible(Statement s) {
		if (individual(s)) {
			RDFNode node = s.getObject();
			Individual i = (Individual)node.as(Individual.class);
			for (OntClass cls : visible)
				if (i.hasRDFType(cls))
					return true;
		}
		return false;
	}
	
}