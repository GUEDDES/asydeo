package com.asydeo.action;

import java.security.Principal;

import thewebsemantic.binding.Jenabean;

import net.sourceforge.stripes.action.ActionBeanContext;
import static com.asydeo.servlet.RequestConstants.*;

import com.asydeo.domain.AsydeoSession;
import com.asydeo.domain.User;
import com.asydeo.servlet.RequestConstants;
import com.asydeo.view.Util;
import com.asydeo.view.View;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;


public class ASDContext extends ActionBeanContext {

	public OntModel getModel() {
		String modelName = getSession().getModelName();
		return (OntModel)getServletContext().getAttribute(modelName);
	}

	public OntModel getRawModel() {
		return (OntModel)getServletContext().getAttribute(RAWMODEL);
	}
	
	public View[] getViews(Individual i) {
		return Util.getView(i);
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

	public AsydeoSession getSession() {
		return (AsydeoSession)getRequest().getSession().getAttribute(RequestConstants.SESSION);
	}
}
