package com.example.vvs.InventoryService.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AccommodationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @NonNull
    @Column(unique = true)
    String name;

    @NonNull
    @Column(unique = true)
    String code;

    @JoinColumn(name = "location_id")
    @NonNull
    @ManyToOne
    Resort resort;

    @Enumerated(EnumType.ORDINAL)
    @NonNull
    AccoType type ;

    @NonNull
    Integer capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "accommodationType")
    List<Unit> units;

    String description;
}
