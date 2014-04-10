package com.data.generator.google.place.api.model;

import com.google.api.client.util.Key;

public class Place {

	@Key
	public String id;

	@Key
	public String name;

	@Key
	public String reference;

	@Key
	public String adr_address;

	// @Override
	// public String toString() {
	// // return name + " - " + id + " - " + reference;
	// return name + " - " + id + " - " + reference;
	// }

	@Override
	public String toString() {
		return name + " - " + adr_address;
	}
}
