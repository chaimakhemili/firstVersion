package com.workshop.formationBack.service;

import com.workshop.formationBack.model.Personne;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;

public interface PersonneService {
    Personne addOrEditPersonne(Personne personne);

    void deletePersonne(Personne personne);

    UserDetails loadUserByUsername(String username);

    List<Personne> getPersonnes();

    Optional<Personne> getPersonneById(Long personneId);

}
