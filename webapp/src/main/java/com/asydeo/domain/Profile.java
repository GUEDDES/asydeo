package com.asydeo.domain;

import java.util.ArrayList;
import java.util.Collection;

import thewebsemantic.Id;

public class Profile {
	@Id
	String id;
	Collection<String> collectedItems = new ArrayList<String>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Collection<String> getCollectedItems() {
		return collectedItems;
	}
	public void setCollectedItems(Collection<String> collectedItems) {
		this.collectedItems = collectedItems;
	}
}
