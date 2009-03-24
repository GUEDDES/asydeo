package com.asydeo.domain;

import java.security.Principal;

import thewebsemantic.Id;

public class Role implements Principal{
	@Id
	String name;

	@Override
	public String getName() {
		return name;
	}

}
