package com.workshop.formationBack.model;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@ToString
@Entity
@Table(name = "personne", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
public class Personne{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min=3, max = 50)
    private String nom;
    @NotBlank
    @Size(min=3, max = 50)
    private String prenom;
    @NaturalId
    @NotBlank
    @Size(min=3, max = 50)
    private String username;
    private String dateDeNaissance;

    private String age;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    private Boolean etat;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public Personne(String nom, String prenom, String username, String dateDeNaissance, String age, String password) {

        this.nom = nom;
        this.prenom = prenom;
        this.username = username;
        this.dateDeNaissance = dateDeNaissance;
        this.age = age;
        this.password=password;
        this.etat = false;
    }

    public Personne() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getDateDeNaissance() {
        return dateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        this.dateDeNaissance = dateDeNaissance;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getEtat() {
        return etat;
    }

    public void setEtat(Boolean etat) {
        this.etat = etat;
    }
}
