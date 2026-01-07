package org.example.elementservice.service;

import org.example.elementservice.model.SimplifiedMountain;
import org.example.elementservice.repository.SimplifiedMountainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SimplifiedMountainService {

    private final SimplifiedMountainRepository mountainRepo;

    @Autowired
    public SimplifiedMountainService(SimplifiedMountainRepository mountainRepo) {
        this.mountainRepo = mountainRepo;
    }

    public void createOrUpdate(UUID id, String name) {
        SimplifiedMountain m = new SimplifiedMountain(id, name);
        mountainRepo.save(m);
    }

    public Optional<SimplifiedMountain> findById(UUID id) {
        return mountainRepo.findById(id);
    }

    public void deleteById(UUID id) {
        mountainRepo.deleteById(id);
    }


}