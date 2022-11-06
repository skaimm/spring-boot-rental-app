package com.rental.app.rental.entities;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;

@Entity
@Table(name = "rental_images")
@Data
public class RentalImage {

    @Id
    @GenericGenerator(name = "RentalImage",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "rental_images_seq")
            })
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "RentalImage")
    @Column(updatable = false)
    private Integer id;

    private String url;
}