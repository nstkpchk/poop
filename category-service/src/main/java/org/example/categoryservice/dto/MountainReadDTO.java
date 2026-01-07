package org.example.categoryservice.dto;


import lombok.Data;


import java.util.UUID;

@Data
public class MountainReadDTO {
    private UUID id;
    private String name;
    private int height;
}
