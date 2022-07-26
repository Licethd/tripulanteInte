package Model.Tripulante;

import Event.PersonalRegistrado;
import core.AggregateRoot;
import java.util.UUID;

public class Tripulante extends AggregateRoot<UUID> {

	private String Nombre;
	private String Apellido;
	private String EmailAddress;

	Cargo Cargo;
	private Tierra cargo_tierra;
	private Aire cargo_aire;

	public Tripulante() {}

	public Tripulante(
		String nombre,
		String apellido,
		String emailAddress,
		Cargo cargo
	) {
		key = UUID.randomUUID();
		this.Nombre = nombre;
		this.Apellido = apellido;
		this.EmailAddress = emailAddress;
		this.Cargo = cargo;
	}

	public void eventCreado() {
		addDomainEvent(new PersonalRegistrado(key, Nombre));
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		this.Apellido = apellido;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.EmailAddress = emailAddress;
	}

	public Cargo getCargo() {
		return Cargo;
	}

	public void setCargo(Cargo cargo) {
		this.Cargo = cargo;
	}
}
