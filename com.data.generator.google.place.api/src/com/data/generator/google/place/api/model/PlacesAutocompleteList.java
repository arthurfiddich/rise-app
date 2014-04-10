package com.data.generator.google.place.api.model;

import java.util.List;

import com.google.api.client.util.Key;

public class PlacesAutocompleteList {

	@Key
	public List<PlaceAutoComplete> predictions;
}
