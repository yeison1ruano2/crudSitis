package com.example.demo.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Personas")
public class Persona {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nombreusuario", length=100, nullable=false)
	private String nombreusuario;

	@Column(name = "Correo", length=100, nullable=false)
	private String correo;

	@Column(name = "Contraseña", length=100, nullable=false)
	private String contrasena;
	
	@Column(name = "Perfil" , length=100, nullable=false)
	private String perfil;
	
	public Persona() {
		
	}

	public Persona(String nombreusu, String correo, String contrasena, String perfil) {
		super();
		this.nombreusuario = nombreusu;
		this.correo = correo;
		this.contrasena = contrasena;
		this.perfil = perfil;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombreusuario() {
		return nombreusuario;
	}

	public void setNombreusuario(String nombreusu) {
		this.nombreusuario = nombreusu;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	
	@Override
	public String toString() {
		return "Persona [id=" + id + ", Nombre Usuario=" + nombreusuario + ", Correo=" + correo + ", Perfil=" + perfil + "]";
	}
	
}