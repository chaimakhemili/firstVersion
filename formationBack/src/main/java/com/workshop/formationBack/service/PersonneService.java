package com.workshop.formationBack.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.workshop.formationBack.model.Personne;
import com.workshop.formationBack.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonneService {

        @Autowired
        private PersonneRepository personnerepository;

        public boolean isPersonnePresent(Long personneId) {
            return personnerepository.existsById(personneId);
        }

        public Personne getPersonneById(Long personneId) {
            return personnerepository.findById(personneId).orElse(null);
        }

        public List<Personne> getPersonnes() {
            List<Personne> personnes = new ArrayList<>();
            Iterator<Personne> personneIterator = personnerepository.findAll().iterator();
            while (personneIterator.hasNext()) {
                personnes.add(personneIterator.next());
            }
            return personnes;
        }

        public Personne addPersonne(Personne personne) {
            return personnerepository.save(personne);
        }

        public Personne updatePersonne(Long personneId, Personne personne) {
            Personne personneToBeUpdated = getPersonneById(personneId);
            personneToBeUpdated.setNom(personne.getNom());
            return personnerepository.save(personneToBeUpdated);
        }

        public void deletePersonne(Long personneId) {
            personnerepository.delete(getPersonneById(personneId));
        }
    }


