package org.example.elementservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TrailListDTO {
    private UUID id;
    private String name;
    private String difficulty;
}
