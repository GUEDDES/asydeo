package com.asydeo.action;

import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.view.OntView;
import com.hp.hpl.jena.ontology.Individual;

@UrlBinding("/asset/list")
public class ListAction extends BaseAction {
	
	String uri;
	String deleteUri;
	int offset;
	
	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@DefaultHandler
	public Resolution start() {			
		return new ForwardResolution("/list.jsp");
	}
	
	@HandlesEvent("delete")
	public Resolution delete() {
		Individual i = individual(deleteUri);
		if ( i!=null )
			i.remove();
		return new RedirectResolution("/asset/list?uri=" + uri);
	}
	
	public Collection<OntView> getList() {	
		return new each(ontClass(uri)) { 
			void $() {if (!item.isAnon()) result.add(OntView.$(item));}}.sorted();	
	}

	public OntView getOntView() {
		return OntView.$(ontClass(uri));
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getDeleteUri() {
		return deleteUri;
	}

	public void setDeleteUri(String deleteUri) {
		this.deleteUri = deleteUri;
	}
}
