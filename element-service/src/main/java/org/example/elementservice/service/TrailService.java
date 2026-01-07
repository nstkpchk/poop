package org.example.elementservice.service;

import org.example.elementservice.model.SimplifiedMountain;
import org.example.elementservice.model.Trail;
import org.example.elementservice.repository.SimplifiedMountainRepository;
import org.example.elementservice.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrailService {

    private final TrailRepository trailRepository;
    private final SimplifiedMountainRepository simplifiedMountainRepository;

    @Autowired
    public TrailService(TrailRepository trailRepository, SimplifiedMountainRepository simplifiedMountainRepository) {
        this.trailRepository = trailRepository;
        this.simplifiedMountainRepository = simplifiedMountainRepository;
    }

    public List<Trail> findAll() {
        return trailRepository.findAll();
    }

    public Optional<Trail> findById(UUID id) {
        return trailRepository.findById(id);
    }

    public Trail save(Trail trail) {
        return trailRepository.save(trail);
    }

    public void deleteById(UUID id) {
        trailRepository.deleteById(id);
    }

    public void deleteAllByMountainId(UUID mountainId) {
        Optional<SimplifiedMountain> mountainOpt = simplifiedMountainRepository.findById(mountainId);

        if (mountainOpt.isPresent()) {
            SimplifiedMountain mountain = mountainOpt.get();

            List<Trail> trails = trailRepository.findByMountain(mountain);
            trailRepository.deleteAll(trails);
        }
    }

    public List<Trail> findByMountain(SimplifiedMountain mountain) {
        return trailRepository.findByMountain(mountain);
    }
}
