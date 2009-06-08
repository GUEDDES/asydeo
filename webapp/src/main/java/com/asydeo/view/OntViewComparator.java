package com.asydeo.view;

import java.util.Comparator;

public class OntViewComparator implements Comparator<OntView> {

	public int compare(OntView o1, OntView o2) {
		return (o1.getLabel().compareTo(o2.getLabel()));
	}

}
