package com.hiberus.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonIgnoreProperties
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    @JsonIgnoreProperties
    private List<UUID> favouritesPizzas;
}
