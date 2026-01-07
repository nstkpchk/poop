package org.example.categoryservice.service;

import org.example.categoryservice.model.Mountain;
import org.example.categoryservice.repository.MountainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MountainService {

    private final MountainRepository mountainRepository;
    private final EventSender eventSender;


    @Autowired
    public MountainService(MountainRepository mountainRepository, EventSender eventSender) {
        this.mountainRepository = mountainRepository;
        this.eventSender = eventSender;
    }

    public List<Mountain> findAll() {
        return mountainRepository.findAll();
    }

    public Optional<Mountain> findById(UUID id) {
        return mountainRepository.findById(id);
    }

    public Mountain save(Mountain mountain) {
        boolean isNew = !mountainRepository.existsById(mountain.getId());
        Mountain saved = mountainRepository.save(mountain);

        if (isNew) {
            eventSender.sendMountainCreated(mountain.getId(), mountain.getName());
        }

        return saved;
    }

    public void deleteById(UUID id) {
        mountainRepository.deleteById(id);
        eventSender.sendMountainDeleted(id);
    }
}
