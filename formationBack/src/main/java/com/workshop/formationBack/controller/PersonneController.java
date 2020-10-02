package com.workshop.formationBack.controller;

import java.util.List;
import java.util.Optional;
import com.workshop.formationBack.model.Personne;
import com.workshop.formationBack.repository.PersonneRepository;
import com.workshop.formationBack.service.PersonneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/rest/api")
    @CrossOrigin(origins = "*")
    public class PersonneController {
        private static Logger logger = LoggerFactory.getLogger(PersonneController.class);
        @Autowired
        private PersonneRepository personneRepository;
        private PersonneService personneService;

        @GetMapping
        public List<Personne> getAllPersonne() {
            return personneRepository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> findById(@PathVariable("id") Long personneId) {
            logger.debug("Retrieving  Person with id {}", personneId);
            Optional<Personne> personne = personneService.getPersonneById(personneId);
            return personne.
                    <ResponseEntity<?>>
                            map(x -> new ResponseEntity<>(x, HttpStatus.FOUND))
                    .orElseGet(() -> new ResponseEntity<>
                            (HttpStatus.NO_CONTENT));
        }

//        @PostMapping("/personnes")
//        public ResponseEntity<?> createPersonne(@RequestBody Personne personne) {
//            if (personneService.findByNom(personne.getNom()).isPresent()) {
//                return new ResponseEntity<>(HttpStatus.IM_USED);
//            } else {
//                Personne personne1 = new Personne(
//                        personne.getNom(),
//                        personne.getPrenom(),
//                        personne.getDateDeNaissance(),
//                        personne.getAge()
//                );
//                personneService.addOrEditPersonne(personne);
//                return new ResponseEntity<>(HttpStatus.CREATED);
//            }
//        }

      @PutMapping("/{id}")
        public ResponseEntity<Personne> updatePersonne(@PathVariable(value = "id") Long personneId, @RequestBody Personne personneDetails) throws ResourceNotFoundException {
            logger.debug("Updating User with id {}", personneId);
            Optional<Personne> currentPerson = personneService.getPersonneById(personneId);
            try {
                if (!currentPerson.isPresent()) {
                    logger.debug("Unable to update. User with id {} not found.", personneId);
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            personneDetails.setId(personneId);
            personneService.addOrEditPersonne(personneDetails);
            return ResponseEntity.ok().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deletePersonne(@PathVariable(value = "id") Long personneId) {
            Optional<Personne> personne = personneService.getPersonneById(personneId);
            try {
                if (!personne.isPresent()) {
                    logger.debug("Unable to delete. User with id {} not found.", personneId);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
            personneService.deletePersonne(personne.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

