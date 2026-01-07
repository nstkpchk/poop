package org.example.elementservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TrailReadDTO {
    private UUID id;
    private String name;
    private double length;
    private String difficulty;
    private String mountainName;
}
