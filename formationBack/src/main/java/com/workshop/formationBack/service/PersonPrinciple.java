package com.workshop.formationBack.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workshop.formationBack.model.Personne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonPrinciple implements UserDetails {
  private static final long serialVersionUID = 1L;
 
    private Long id;
    private String nom;
    private String prenom;
    private String username;

    @JsonIgnore
    private String password;
    private Boolean etat;
    private Collection<? extends GrantedAuthority> authorities;
 
    public PersonPrinciple(Long id, String nom,
                           String prenom, String username, String password, Boolean etat,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.etat=etat;
    }
 
    public static PersonPrinciple build(Personne personne) {
        List<GrantedAuthority> authorities = personne.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());
 
        return new PersonPrinciple(
                personne.getId(),
                personne.getNom(),
                personne.getPrenom(),
                personne.getUsername(),
                personne.getPassword(),
                personne.getEtat(),
                authorities
        );
    }
 
    public Long getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }

    @Override
    public String getUsername() {
        return username;
    }
 
    @Override
    public String getPassword() {
        return password;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        PersonPrinciple personne = (PersonPrinciple) o;
        return Objects.equals(id, personne.id);
    }
}
