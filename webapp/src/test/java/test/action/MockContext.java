package test.action;

import javax.servlet.http.HttpServletRequest;

import com.asydeo.action.ASDContext;
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
		OntClass root = m.createClass("http://asydeo.com/schema#ConfigurableItem");
		OntClass visible = m.createClass("http://asydeo.com/schema#VisibleClass");
		root.addSubClass(c);
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

			public void apply(HttpServletRequest r) {
				// TODO Auto-generated method stub
				
			}

			public String getContent() {
				// TODO Auto-generated method stub
				return null;
			}

			
			public String getContent(Individual i) {
				// TODO Auto-generated method stub
				return null;
			}

			public int getOrder() {
				// TODO Auto-generated method stub
				return 0;
			}

			public void setIndividual(Individual i) {
				// TODO Auto-generated method stub
				
			}

			public void setOntProperty(OntProperty p) {
				// TODO Auto-generated method stub
				
			}

			public void setOrder(int i) {
				// TODO Auto-generated method stub
				
			}
			
		};
		
		return new View[] { v };
	}
}
