package com.asydeo.domain;

import com.asydeo.servlet.RequestConstants;
import static com.asydeo.util.AsydeoConfig.*;

public class AsydeoSession {
	
	String modelName = RequestConstants.CURRENT_MODEL;
	String lang;

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
}
