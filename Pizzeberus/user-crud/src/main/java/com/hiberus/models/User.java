package com.hiberus.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Table(name = "users")
public class User {
    @JsonIgnoreProperties
    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;
    private String name;
    @ElementCollection
    private List<UUID> favouritesPizzas;
}
