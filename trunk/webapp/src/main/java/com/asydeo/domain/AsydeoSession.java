package com.asydeo.domain;

import com.asydeo.servlet.RequestConstants;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

import static com.asydeo.util.AsydeoConfig.*;

public class AsydeoSession {
	
	String modelName = RequestConstants.CURRENT_MODEL;
	String lang;
	OntModel infModel;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
	public String getLang() {
	    if ( lang == null || lang.isEmpty() ) {
	        return getDefaultLang();
	    }
	    return lang;
	}
	
	public void setLang(String lang) {
	    this.lang = lang;
	}

	public OntModel getInferenceModel(OntModel model) {
		if (infModel!=null)
			return infModel;
		infModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF, model);
		infModel.setNsPrefix( getAsydeoPrefix(), getAsydeoNS() );
		return infModel;
	}
}
