package com.rise.validation.impl;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;
import com.rise.common.model.Address;
import com.rise.common.util.checker.Precondition;
import com.rise.validation.Validation;
import com.rise.validation.builders.AddressBuilder;
import com.rise.validaton.GeocodeResponse;

public class AddressValidation implements Validation<Address, String> {

	@Override
	public boolean validate(Address argAddress) throws Exception {
		return validate(argAddress, "en");
	}

	@Override
	public boolean validate(Address argAddress, String argLanguage)
			throws Exception {
		if (Precondition.checkNotNull(argAddress)) {
			String address = new AddressBuilder(argAddress).build();
			if (Precondition.checkNotNull(address)) {
				final Geocoder geocoder = new Geocoder();
				GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
						.setAddress(address).setLanguage(argLanguage)
						.getGeocoderRequest();
				com.google.code.geocoder.model.GeocodeResponse geocoderResponse = geocoder
						.geocode(geocoderRequest);
				String responseCode = geocoderResponse.getStatus().name();
				if (responseCode.equals(GeocodeResponse.Status.OK)) {
					return true;
				}

			}
		}
		return false;
	}

}
