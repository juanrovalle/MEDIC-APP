package com.jrolab.medic_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Medic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedic;

    @ManyToOne()
    @JoinColumn(name = "id_speciality", nullable = false, foreignKey = @ForeignKey(name = "FK_medic_speciality"))
    private Speciality speciality;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(nullable = false, length = 70)
    private String lastname;

    @Column(nullable = false, length = 10)
    private String NPI;

    @Column(length = 250)
    public String photoUrl;

}
