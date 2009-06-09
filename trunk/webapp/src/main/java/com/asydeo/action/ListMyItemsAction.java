package com.asydeo.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.domain.Profile;
import com.asydeo.domain.User;
import com.asydeo.view.OntView;
import com.hp.hpl.jena.ontology.Individual;

@UrlBinding("/asset/mystuff")
public class ListMyItemsAction extends BaseAction {

	String deleteUri;
	
	public String getDeleteUri() {
		return deleteUri;
	}

	public void setDeleteUri(String deleteUri) {
		this.deleteUri = deleteUri;
	}

	@DefaultHandler
	public Resolution start() throws IOException {
		return new ForwardResolution("/myitems.jsp");
	}
	
	@HandlesEvent("delete")
	public Resolution delete() {
		User u = context.getUser();
		Profile p = u.getProfile();
		Individual i = individual(deleteUri);
		if ( i!=null )
			p.getCollectedItems().remove(i.getURI());
		return new RedirectResolution(ListMyItemsAction.class);
	}
	
	
	public Collection<OntView> getList() {
		User u = context.getUser();
		Profile p = u.getProfile();
		Collection<String> c = p.getCollectedItems();
		ArrayList<OntView> items = new ArrayList<OntView>();
		for (String uri : c) {
			Individual i = m().getIndividual(uri);
			items.add(OntView.$(i));
		}
		return items;
	}


}
