package com.hiberus.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Table(name = "users")
public class User {
    @JsonIgnoreProperties
    @Getter
    private UUID id;
    @Getter
    private String name;
    @Getter
    private List<UUID> favouritesPizzas;
}
