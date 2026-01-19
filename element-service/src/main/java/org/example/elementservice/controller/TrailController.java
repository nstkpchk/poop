package org.example.elementservice.controller;

import org.example.elementservice.model.Trail;
import org.example.elementservice.dto.TrailCreateUpdateDTO;
import org.example.elementservice.dto.TrailListDTO;
import org.example.elementservice.dto.TrailReadDTO;
import org.example.elementservice.service.SimplifiedMountainService;
import org.example.elementservice.service.TrailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TrailController {

    private final TrailService trailService;
    private final SimplifiedMountainService simplifiedMountainService;

    public TrailController(TrailService trailService, SimplifiedMountainService simplifiedMountainService) {
        this.trailService = trailService;
        this.simplifiedMountainService = simplifiedMountainService;
    }


    @GetMapping("/trails")
    public ResponseEntity<List<TrailListDTO>>  listTrails(HttpServletRequest request){
        // ------
        System.out.println(">>> [ELEMENT-SERVICE] Obsługuję żądanie GET /api/trails. Wątek: " + Thread.currentThread().getName());
        System.out.println(">>> [ELEMENT-SERVICE] Mój adres lokalny (kontener): " + request.getLocalAddr());
        // --------------------------------
        List<TrailListDTO> trails = trailService.findAll().stream()
                .map(t->{
                    TrailListDTO dto = new TrailListDTO();
                    dto.setId(t.getId());
                    dto.setName(t.getName());
                    dto.setDifficulty(t.getDifficulty());
                    return dto;
                })
                .collect(Collectors.toList());
        if(trails.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trails);

    }

    @GetMapping("/trails/{id}")
    public ResponseEntity<TrailReadDTO> getTrail(@PathVariable UUID id){
        return trailService.findById(id)
                .map(t->{
                    TrailReadDTO dto = new TrailReadDTO();
                    dto.setId(t.getId());
                    dto.setName(t.getName());
                    dto.setLength(t.getLength());
                    dto.setDifficulty(t.getDifficulty());
                    dto.setMountainName(t.getMountain().getName());
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/mountains/{mountainId}/trails")
    public ResponseEntity<List<TrailListDTO>> getTrailsByMountain(@PathVariable UUID mountainId){
        var mountainOpt = simplifiedMountainService.findById(mountainId);
        if(mountainOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var trails = trailService.findByMountain(mountainOpt.get()).stream()
                .map(t->{
                    var dto = new TrailListDTO();
                    dto.setId(t.getId());
                    dto.setName(t.getName());
                    dto.setDifficulty(t.getDifficulty());
                    return dto;
                })
                .collect(Collectors.toList());
        if (trails.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trails);
    }

    @PostMapping("/mountains/{mountainId}/trails")
    public ResponseEntity<Void> addTrailToMountain(@PathVariable UUID mountainId, @RequestBody TrailCreateUpdateDTO dto){

        var mountainOpt = simplifiedMountainService.findById(mountainId);
        if(mountainOpt.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var trail = Trail.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .length(dto.getLength())
                .difficulty(dto.getDifficulty())
                .mountain(mountainOpt.get())
                .build();

        var saved = trailService.save(trail);
        return ResponseEntity.created(URI.create("/api/trails/"+saved.getId())).build();
    }

    @PutMapping("/trails/{id}")
    public ResponseEntity<Void> updateTrail(@PathVariable UUID id, @RequestBody TrailCreateUpdateDTO dto){
        var opt = trailService.findById(id);
        if(opt.isEmpty()){
            return  ResponseEntity.notFound().build();
        }

        var existing = opt.get();

        if(dto.getMountainId()!=null){
            var newMountainOpt = simplifiedMountainService.findById(dto.getMountainId());
            if(newMountainOpt.isEmpty()){
                return ResponseEntity.badRequest().build();
            }
            existing.setMountain(newMountainOpt.get());
        }

        existing.setName(dto.getName());
        existing.setLength(dto.getLength());
        existing.setDifficulty(dto.getDifficulty());
        trailService.save(existing);

        return ResponseEntity.ok().build();

    }


    @DeleteMapping("/trails/{id}")
    public ResponseEntity<Void> deleteTrail(@PathVariable UUID id){
        var opt = trailService.findById(id);
        if(opt.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        trailService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

