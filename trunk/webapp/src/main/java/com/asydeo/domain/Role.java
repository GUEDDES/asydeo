package com.asydeo.domain;

import java.security.Principal;

import thewebsemantic.Id;
import thewebsemantic.binding.RdfBean;

public class Role extends RdfBean<Role> implements Principal {
	@Id
	String name;

	public Role() {}
	
	public Role(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
