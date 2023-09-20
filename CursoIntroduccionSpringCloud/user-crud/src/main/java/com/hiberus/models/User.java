package com.hiberus.models;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Table(name = "users")
public class User {
    private UUID id;
    private String name;
    private List<UUID> favouritesPizzas;
}
