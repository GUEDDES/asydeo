package com.asydeo.action;

import static com.asydeo.servlet.RequestConstants.*;

import java.security.Principal;

import net.sourceforge.stripes.action.ActionBeanContext;
import thewebsemantic.binding.Jenabean;

import com.asydeo.domain.AsydeoSession;
import com.asydeo.domain.User;
import com.asydeo.servlet.RequestConstants;
import com.asydeo.view.View;
import com.asydeo.view.ViewManager;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;


public class ASDContext extends ActionBeanContext {
	ViewManager vm;

	public ASDContext() {
		super();
		vm = new ViewManager();
	}
	
	public OntModel getModel() {
		String modelName = getSession().getModelName();
		
		//create an in session inf model
		if (INF_MODEL.equals(modelName)) {
			OntModel model = (OntModel)getServletContext().getAttribute(CURRENT_MODEL);
			return getSession().getInferenceModel(model);
		}
			
		return (OntModel)getServletContext().getAttribute(modelName);
	}

	public OntModel getRawModel() {
		return (OntModel)getServletContext().getAttribute(RAWMODEL);
	}
	
	public View[] getViews(Individual i, OntClass type) {
		return vm.getView(getRawModel(),i, type);
	}

	public View[] getViews(String classUri) {
		return vm.getView(getModel(), classUri);
	}
	
	public User getUser() {
		Principal p = getRequest().getUserPrincipal();
		if ( p == null)
			return null;
		User u = (User)getRequest().getSession().getAttribute(USER);
		if (u == null) {
			u = Jenabean.load(User.class, p.getName());
			getRequest().getSession().setAttribute(USER, u);
		}
		return u;
	}
	
	public boolean isAuthenticated() {
		return getRequest().getUserPrincipal() != null;
	}

	public AsydeoSession getSession() {
		return (AsydeoSession)getRequest().getSession().getAttribute(RequestConstants.SESSION);
	}
	
	

}
