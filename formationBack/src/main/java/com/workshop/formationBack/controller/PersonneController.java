package com.workshop.formationBack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.workshop.formationBack.model.Personne;
import com.workshop.formationBack.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/rest/api")
    public class PersonneController {
        @Autowired
        private PersonneRepository personneRepository;

        @GetMapping("/personnes")
        public List<Personne> getAllPersonne() {
            return personneRepository.findAll();
        }

        @GetMapping("/personnes/{id}")
        public ResponseEntity<Personne> getPersonneById(@PathVariable(value = "id") Long personneId)
                throws ResourceNotFoundException {
            Personne personne = personneRepository.findById(personneId)
                    .orElseThrow(() -> new ResourceNotFoundException("Personne not found for this id :: " + personneId));
            return ResponseEntity.ok().body(personne);
        }

        @PostMapping("/personnes")
        public Personne createPersonne(@RequestBody Personne personne) {
            return personneRepository.save(personne);
        }

        @PutMapping("/personnes/{id}")
        public ResponseEntity<Personne> updatePersonne(@PathVariable(value = "id") Long personneId,@RequestBody Personne personneDetails) throws ResourceNotFoundException{
            Personne personne = personneRepository.findById(personneId)
                    .orElseThrow(() -> new ResourceNotFoundException("Personne not found for this id :: " + personneId));

            personne.setNom(personneDetails.getNom());
            personne.setPrenom(personneDetails.getPrenom());
            personne.setDateDeNaissance(personneDetails.getDateDeNaissance());
            personne.setAge(personneDetails.getAge());
            final Personne updatedPersonne = personneRepository.save(personne);
            return ResponseEntity.ok(updatedPersonne);
        }

        @DeleteMapping("/personnes/{id}")
        public Map<String, Boolean> deletePersonne(@PathVariable(value = "id") Long personneId)
                throws ResourceNotFoundException {
            Personne personne = personneRepository.findById(personneId)
                    .orElseThrow(() -> new ResourceNotFoundException("Personne not found for this id :: " + personneId));

            personneRepository.delete(personne);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
        }
    }

