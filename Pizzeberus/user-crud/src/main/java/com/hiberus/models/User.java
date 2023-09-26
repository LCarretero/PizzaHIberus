package com.hiberus.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Builder
@Getter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonIgnoreProperties
    @Id
    private UUID id;
    private String name;
    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    private Set<UUID> favouritesPizzas;
}
