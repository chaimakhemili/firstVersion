package com.workshop.formationBack.service;

import com.workshop.formationBack.model.Contrat;
import com.workshop.formationBack.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ContratServiceImpl implements ContratService{

    @Autowired
    private ContratRepository contratRepository;
    @Override
    public Contrat addOrEditContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public void deleteContrat(Contrat contrat) {
        contratRepository.delete(contrat);
    }

    @Override
    public Optional<Contrat> findByContratNom(String contratNom) {
        return contratRepository.findByContratNom(contratNom);

    }

    @Override
    public List<Contrat> getContrats() {
        return contratRepository.findAll();
    }

    @Override
    public Optional<Contrat> getContratById(Long contratId) {
        return contratRepository.findById(contratId);
    }
}
