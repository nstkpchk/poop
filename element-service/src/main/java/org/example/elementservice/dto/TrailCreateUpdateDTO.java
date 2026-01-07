package org.example.elementservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TrailCreateUpdateDTO {
    private String name;
    private double length;
    private String difficulty;
    private UUID mountainId;
}
