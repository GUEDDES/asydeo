package com.asydeo.domain;

import java.util.Collection;

import thewebsemantic.Id;

/**
 * @author SG0897954
 *
 */
public class User {
	@Id
	String username;
	String passwordHash;
	Collection<Role> roles;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

}
