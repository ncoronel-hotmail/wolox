package com.ejercicio.wolox.EjercicioWolox.model;

public class GeoModel {

	private String lat;

	private String lgn;

	public GeoModel() {
		super();

	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLgn() {
		return lgn;
	}

	public void setLgn(String lgn) {
		this.lgn = lgn;
	}

	@Override
	public String toString() {
		return "GeoResponse [lat=" + lat + ", lgn=" + lgn + "]";
	}

}
