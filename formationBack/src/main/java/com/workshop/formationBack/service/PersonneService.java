package com.workshop.formationBack.service;

import com.workshop.formationBack.model.Personne;
import java.util.List;
import java.util.Optional;

public interface PersonneService {
    Personne addOrEditPersonne(Personne personne);

    void deletePersonne(Personne personne);


    List<Personne> getPersonnes();

    Optional<Personne> getPersonneById(Long personneId);

}
