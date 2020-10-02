package com.workshop.formationBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Entity
@Table(name="contrat")
public class Contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contratId;

    private String contratNom;

    @OneToMany(mappedBy = "personne",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Personne> personnes;

    public Contrat(String contratNom) {
        this.contratNom = contratNom;

    }
    public Long getContratId() {
        return contratId;
    }

    public void setContratId(Long contratId) {
        this.contratId = contratId;
    }

    public String getContratNom() {
        return contratNom;
    }

    public void setContratNom(String contratNom) {
        this.contratNom = contratNom;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }
}



