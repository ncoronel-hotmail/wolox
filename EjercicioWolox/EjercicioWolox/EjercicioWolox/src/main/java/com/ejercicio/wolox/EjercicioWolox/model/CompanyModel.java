package com.ejercicio.wolox.EjercicioWolox.model;

public class CompanyModel {

	private String name;

	private String catchPhrase;

	private String bs;

	public CompanyModel() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatchPhrase() {
		return catchPhrase;
	}

	public void setCatchPhrase(String catchPhrase) {
		this.catchPhrase = catchPhrase;
	}

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	@Override
	public String toString() {
		return "CompanyResponse [name=" + name + ", catchPhrase=" + catchPhrase + ", bs=" + bs + "]";
	}

}
