package com.example.demo.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Persona;
import com.example.demo.repository.PersonaRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PersonaControlador {
	  
	@Autowired
	PersonaRepository personaRepository;
	
	@GetMapping ("/personas")
	  public ResponseEntity<List<Persona>> getAllPersona(@RequestParam(required = false) String perfil) {
	    try {
	      List<Persona> persona = new ArrayList<Persona>();

	      if (perfil == null)
	        personaRepository.findAll().forEach(persona::add);
	      else
	        personaRepository.findByPerfilContaining(perfil).forEach(persona::add);

	      if (persona.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(persona, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@GetMapping("/personas/{id}")
	  public ResponseEntity<Persona> getPersonaById(@PathVariable("id") long id) {
	    Optional<Persona> personaData = personaRepository.findById(id);

	    if (personaData.isPresent()) {
	      return new ResponseEntity<>(personaData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PostMapping ("/personas")
	  public ResponseEntity<Persona> createPersona(@RequestBody Persona persona) {
	    try {
	      Persona _persona = personaRepository
	          .save(new Persona(persona.getNombreusuario(), persona.getCorreo(),persona.getContrasena(),persona.getPerfil()));
	      return new ResponseEntity<>(_persona, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@PutMapping("/personas/{id}")
	  public ResponseEntity<Persona> updatePersona(@PathVariable("id") long id, @RequestBody Persona persona) {
	    Optional<Persona> personaData = personaRepository.findById(id);

	    if (personaData.isPresent()) {
	      Persona _persona = personaData.get();
	      _persona.setNombreusuario(persona.getNombreusuario());
	      _persona.setCorreo(persona.getCorreo());
	      return new ResponseEntity<>(personaRepository.save(_persona), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }

	  @DeleteMapping("/personas/{id}")
	  public ResponseEntity<HttpStatus> deletePersona(@PathVariable("id") long id) {
	    try {
	      personaRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

	  @DeleteMapping ("/personas")
	  public ResponseEntity<HttpStatus> deleteAllPersona() {
	    try {
	      personaRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	  }
}
