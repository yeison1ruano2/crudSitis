package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.modelo.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>{
	List<Persona>findByPerfilContaining(String perfil);
}
