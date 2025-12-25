package com.example.vvs.InventoryService.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "acco_type_id", nullable = false)
    private AccommodationType accommodationType;

    @Column(nullable = false)
    private String unitNumber;

    @Column(name = "active", nullable = false)
    private Boolean isActive;
}

