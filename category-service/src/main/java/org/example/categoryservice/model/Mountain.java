package org.example.categoryservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Builder
@Data
@Entity
@Table(name="mountains")
@NoArgsConstructor
@AllArgsConstructor
public class Mountain implements Comparable<Mountain>, Serializable{

    @Id
    private UUID id;

    @Column(name="mountain_name")
    private String name;

    @Column(name="mountain_height")
    private int height;


    @Override
    public int compareTo(Mountain other)
    {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return String.format("Mountain [name='%s', height=%dm]",name,height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mountain m)) return false;
        return Objects.equals(name, m.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}

