package com.rental.app.rental.entities;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.SEQUENCE;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "rentals")
@Data
@NoArgsConstructor
@Accessors(fluent = true)
@Getter
@Setter
public class Rental {

    @Id
    @GenericGenerator(name = "Rental",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "rentals_seq")
            })
    @GeneratedValue(strategy = SEQUENCE, generator = "Rental")
    @Column(updatable = false)
    private Integer id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private int sleeps;
    @Column
    private long pricePerDay;
    @Column
    private String homeCity;
    @Column
    private String homeState;
    @Column
    private String homeZip;
    @Column
    private String homeCounty;
    @Column
    private String homeCountry;
    @Column
    private String vehicleMake;
    @Column
    private String vehicleModel;
    @Column
    private int vehicleYear;
    @Column
    private BigDecimal vehicleLength;
    @Column(name = "created", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant created;
    @Column(name = "updated", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private Instant updated;
    @Column
    private double lat;
    @Column
    private double lng;
    @Column
    private String primaryImageUrl;
    @Column
    private String ownerName;
    @Column
    private String ownerAvatarUrl;

    @OneToMany(fetch = EAGER, cascade = ALL, orphanRemoval = true)
    @JoinColumn(name="rental_id")
    private List<RentalImage> images;
}