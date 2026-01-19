package org.example.elementservice.controller;

import org.example.elementservice.dto.SimplifiedMountainDTO;
import org.example.elementservice.service.SimplifiedMountainService;
import org.example.elementservice.service.TrailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/internal/mountains")
public class CategoryNotificationController {

    private final TrailService trailService;
    private final SimplifiedMountainService mountainService;

    public CategoryNotificationController(TrailService trailService, SimplifiedMountainService mountainService) {
        this.trailService = trailService;
        this.mountainService = mountainService;
    }

    @DeleteMapping("/{id}")
    public void handleMountainDeleted(@PathVariable UUID id) {
        // ------
        System.out.println(">>> [ELEMENT-SERVICE] Otrzymałem polecenie usunięcia góry: " + id);
        // --------------------------------
        trailService.deleteAllByMountainId(id);
        mountainService.deleteById(id);
    }

    @PostMapping
    public void handleMountainCreated(@RequestBody SimplifiedMountainDTO dto) {
        mountainService.createOrUpdate(dto.getId(), dto.getName());
    }
}
