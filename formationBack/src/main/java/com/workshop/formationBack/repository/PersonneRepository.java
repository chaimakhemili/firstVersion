package com.workshop.formationBack.repository;

import com.workshop.formationBack.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long>{
    Optional<Personne> findByUsername(String username);
    Boolean existsByUsername(String username);

}

