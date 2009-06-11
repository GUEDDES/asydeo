package com.asydeo.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class GraphFilter {

	Model model;
	int maxdepth;
	HashSet<OntClass> visible;
	Map<String, String> visited;
	
	public GraphFilter(int maxdepth) {
		this.maxdepth = maxdepth;
		visible = new HashSet<OntClass>();
		visited = new HashMap<String, String>();
	}

	public void addVisible(OntClass c) {
		visible.add(c);
	}
	
	public Model filter(Individual n) {
		model = ModelFactory.createDefaultModel();
		filter(n, n.listProperties(), 0);	
		return model;
	}
	
	public void filter(Individual n, StmtIterator statements, int depth) {	
		if (depth>maxdepth)
			return;
		
		if (visited.containsKey(n.getURI()))
			return;
		else
			visited.put(n.getURI(), n.getURI());
		
		while(statements.hasNext()) {
			Statement s = statements.nextStatement();
			RDFNode r = s.getObject();
			//ignore literals
			if (!r.canAs(Individual.class))
				continue;			
			Individual i = (Individual)s.getObject().as(Individual.class);
			if (i.isClass() || i.isProperty())
				continue;

			
			if (visible(s)) {
				model.add(n, s.getPredicate(), s.getObject());			
				filter(i, i.listProperties(), depth+1);
			} else {
				filter(n, i.listProperties(), depth+1);
			}
		}
	}

	private boolean individual(Statement s) {
		RDFNode node = s.getObject();
		Individual i = (Individual)node.as(Individual.class);
		
		Resource r = s.getModel().getResource("http://asydeo.com/schema#ConfigurableItem");
		return i.hasRDFType(r);
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
