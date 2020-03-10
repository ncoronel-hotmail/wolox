package com.ejercicio.wolox.EjercicioWolox.model;

public class AddressModel {

	private String street;

	private String suite;

	private String city;

	private String zipcode;

	private GeoModel geo;

	public AddressModel() {
		super();
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public GeoModel getGeo() {
		return geo;
	}

	public void setGeo(GeoModel geo) {
		this.geo = geo;
	}

	@Override
	public String toString() {
		return "AddressResponse [street=" + street + ", suite=" + suite + ", city=" + city + ", zipcode=" + zipcode
				+ ", geo=" + geo + "]";
	}

}
