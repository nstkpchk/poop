package org.example.elementservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Builder
@Data
@Entity
@Table(name = "simplified_mountains")
@NoArgsConstructor
@AllArgsConstructor
public class SimplifiedMountain {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    // getters & setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

