package com.asydeo.domain;

import java.util.Collection;

import thewebsemantic.Id;

public class User {
	@Id
	String username;
	String passwordHash;
	Collection<Role> roles;

}
