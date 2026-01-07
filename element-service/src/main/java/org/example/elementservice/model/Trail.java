package org.example.elementservice.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Builder
@Data
@Entity
@Table(name="trails")
@NoArgsConstructor
@AllArgsConstructor
public class Trail implements Comparable<Trail>, Serializable{

    @Id
    private UUID id;

    @Column(name="trail_name")
    private String name;

    @Column(name="trail_length")
    private double length;

    @Column(name="trail_difficulty")
    private String difficulty;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name="mountain_id")
    private SimplifiedMountain mountain;


    @Override
    public int compareTo(Trail other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        String mountainName = (mountain != null) ? mountain.getName() : "Unknown";
        return String.format("    Trail [name='%s', length=%.1fkm, difficulty='%s', mountain='%s']",
                name, length, difficulty, mountainName);
    }

    @Override
    public boolean equals(Object o)
    {
        if(this==o) return true;
        if(!(o instanceof Trail)) return false;
        Trail t = (Trail) o;
        return Objects.equals(name,t.name) && Objects.equals(mountain.getName(),t.mountain.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mountain.getName());
    }

}
