package com.workshop.formationBack.controller;

import com.workshop.formationBack.model.Contrat;
import com.workshop.formationBack.repository.ContratRepository;
import com.workshop.formationBack.service.ContratService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/api")
@CrossOrigin(origins = "*")
public class ContratController {
    private static Logger logger = LoggerFactory.getLogger(ContratController.class);
    @Autowired
    private ContratRepository contratRepository;
    private ContratService contratService;

    @GetMapping
    public List<Contrat> getAllContrat() {
        return contratRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long contratId) {
        logger.debug("Retrieving  Contrat with id {}", contratId);
        Optional<Contrat> contrat = contratService.getContratById(contratId);
        return contrat.
                <ResponseEntity<?>>
                        map(x -> new ResponseEntity<>(x, HttpStatus.FOUND))
                .orElseGet(() -> new ResponseEntity<>
                        (HttpStatus.NO_CONTENT));
    }

    @PostMapping("/contrats")
    public ResponseEntity<?> addAccessRequest(@Valid @RequestBody Contrat contrat) {
        if (contratService.findByContratNom(contrat.getContratNom()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        } else {
            Contrat c= new Contrat(contrat.getContratNom());
            contratService.addOrEditContrat(c);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> updateContrat(@PathVariable(value = "id") Long contratId, @RequestBody Contrat contratDetails) throws ResourceNotFoundException {
        logger.debug("Updating Contrat with id {}", contratId);
        Optional<Contrat> currentContrat = contratService.getContratById(contratId);
        try {
            if (!currentContrat.isPresent()) {
                logger.debug("Unable to update. User with id {} not found.", contratId);
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        contratDetails.setContratId(contratId);
            contratService.addOrEditContrat(contratDetails);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContrat(@PathVariable(value = "id") Long contratId) {
        Optional<Contrat> contrat = contratService.getContratById(contratId);
        try {
            if (!contrat.isPresent()) {
                logger.debug("Unable to delete. Contrat with id {} not found.", contratId);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        contratService.deleteContrat(contrat.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


