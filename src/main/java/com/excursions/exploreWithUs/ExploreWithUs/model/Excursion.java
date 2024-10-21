package com.excursions.exploreWithUs.ExploreWithUs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "excursion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    Double price;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime creationDate;
}
