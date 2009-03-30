package com.asydeo.domain;

import com.asydeo.servlet.RequestConstants;

public class AsydeoSession {
	
	String modelName = RequestConstants.CURRENT_MODEL;

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
