package com.workshop.formationBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Periode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long periodeId;
    private Date dateDebut;
    private Date dateFin;

}
