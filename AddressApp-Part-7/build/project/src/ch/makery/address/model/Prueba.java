package ch.makery.address.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Prueba {
	private final StringProperty nombre;
	private final StringProperty apellido;
	
	public Prueba() {
		this(null, null);
	}
	
	/**
	 * Constructor with some initial data.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public Prueba(String firstName, String lastName) {
		this.nombre = new SimpleStringProperty(firstName);
		this.apellido = new SimpleStringProperty(lastName);
	}

	public StringProperty getNombre() {
		return nombre;
	}

	public StringProperty getApellido() {
		return apellido;
	}
	public String getNomeString() {
		return nombre.get();
	}
	public String getApellidoString() {
		return apellido.get();
	}
}
