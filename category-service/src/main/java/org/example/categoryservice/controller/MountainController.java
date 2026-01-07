package org.example.categoryservice.controller;

import org.example.categoryservice.model.Mountain;
import org.example.categoryservice.dto.MountainCreateUpdateDTO;
import org.example.categoryservice.dto.MountainListDTO;
import org.example.categoryservice.dto.MountainReadDTO;
import org.example.categoryservice.service.MountainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/mountains")
public class MountainController {

    private final MountainService mountainService;


    public MountainController(MountainService mountainService) {
        this.mountainService = mountainService;
        }

    @GetMapping
    public ResponseEntity<List<MountainListDTO>> listMountains(){
        List<MountainListDTO> mountains = mountainService.findAll().stream()
                .map(m->{
                    MountainListDTO dto = new MountainListDTO();
                    dto.setId(m.getId());
                    dto.setName(m.getName());
                    return dto;
                })
                .collect(Collectors.toList());
        if(mountains.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(mountains);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MountainReadDTO> getMountain(@PathVariable UUID id){
        return mountainService.findById(id)
                .map(m->{
                    MountainReadDTO dto = new MountainReadDTO();
                    dto.setId(m.getId());
                    dto.setName(m.getName());
                    dto.setHeight(m.getHeight());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> addMountain(@RequestBody MountainCreateUpdateDTO dto){
        Mountain mountain = Mountain.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .height(dto.getHeight())
                .build();
        Mountain added = mountainService.save(mountain);
        return ResponseEntity.created(URI.create("/api/mountains/"+added.getId())).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMountain(@PathVariable UUID id,@RequestBody MountainCreateUpdateDTO dto){
        var opt = mountainService.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Mountain existing = opt.get();
        existing.setName(dto.getName());
        existing.setHeight(dto.getHeight());
        mountainService.save(existing);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMountain(@PathVariable UUID id){
        System.out.println("Próba usunięcia UUID: " + id);

        // Wyświetlamy wszystkie UUID w bazie dla debugowania
        System.out.println("Aktualne UUID w bazie:");
        mountainService.findAll().forEach(m -> System.out.println(m.getId()));

        // 1️⃣ Usuwamy rekord lokalnie (idempotentnie)
        boolean exists = mountainService.findById(id).isPresent();
        if (exists) {
            mountainService.deleteById(id);
            System.out.println("Mountain " + id + " został usunięty z category-service");
        } else {
            System.out.println("Mountain " + id + " nie istnieje w category-service, nic nie usuwamy");
        }

        // 2️⃣ Zawsze zwracamy 204 – idempotentny DELETE
        return ResponseEntity.noContent().build();
    }
}
