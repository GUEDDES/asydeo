package test.action;

import javax.servlet.http.HttpServletRequest;

import com.asydeo.action.ASDContext;
import com.asydeo.view.Util;
import com.asydeo.view.View;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import net.sourceforge.stripes.action.ActionBeanContext;

@SuppressWarnings("unused")
public class MockContext extends ASDContext  {
	
	OntModel m;
	
	public MockContext() {
		m = ModelFactory.createOntologyModel();
		m.setNsPrefix("foo", "http://foo#");
		OntClass c = m.createClass("http://foo#dodad");
		c.createIndividual("http://foo#1");
		c.createIndividual("http://foo#2");
		m.createOntProperty("http://foo#property");
	}
	
	public OntModel getModel() {
		return m;
	}

	public OntModel getRawModel() {
		return m;
	}
	
	public View[] getViews(Individual i) {
		View v = new View() {

			@Override
			public void apply(HttpServletRequest r) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getContent() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getContent(Individual i) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getOrder() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void setIndividual(Individual i) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setOntProperty(OntProperty p) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setOrder(int i) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		return new View[] { v };
	}
}
