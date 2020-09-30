package com.workshop.formationBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class contrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contratId;

    private String contratNom;
}
