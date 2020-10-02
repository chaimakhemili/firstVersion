package com.workshop.formationBack.service;

import com.workshop.formationBack.model.Contrat;
import java.util.List;
import java.util.Optional;

public interface ContratService {
    Contrat addOrEditContrat(Contrat contrat);

    void deleteContrat(Contrat contrat);

    Optional<Contrat> findByContratNom(String contratNom);

    List<Contrat> getContrats();

    Optional<Contrat> getContratById(Long contratId);
}
