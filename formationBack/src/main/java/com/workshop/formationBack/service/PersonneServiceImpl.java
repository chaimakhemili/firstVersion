package com.workshop.formationBack.service;
import java.util.List;
import java.util.Optional;
import com.workshop.formationBack.model.Personne;
import com.workshop.formationBack.repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonneServiceImpl implements PersonneService {

    @Autowired
    private PersonneRepository personnerepository;


    public Personne addOrEditPersonne(Personne personne) {
        return personnerepository.save(personne);
    }

    public void deletePersonne(Personne personne) {
        personnerepository.delete(personne);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Personne user = personnerepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Person Not Found with -> username : " + username)
                );

        return PersonPrinciple.build(user);
    }


    public List<Personne> getPersonnes() {
        return personnerepository.findAll();
    }

    public Optional<Personne> getPersonneById(Long personneId) {
        return personnerepository.findById(personneId);
    }


}


