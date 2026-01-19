package org.example.categoryservice.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import java.util.UUID;

@Service
public class EventSender {
    private final RestTemplate rest;

    @Value("${trail.service.url}")
    private String trailServiceUrl;

    public EventSender(RestTemplate rest) {
        this.rest = rest;
    }

    public void sendMountainDeleted(UUID id) {
        try {
            rest.delete(trailServiceUrl+"/internal/mountains/" + id);
        } catch (Exception ex) {
            System.err.println("Nie udało się wysłać eventu usunięcia: " + ex.getMessage());
        }
    }

    public void sendMountainCreated(UUID id, String name) {
        try {
            var body = Map.of(
                    "id", id.toString(),
                    "name", name
            );
            rest.postForObject(trailServiceUrl+"/internal/mountains", body, Void.class);
        } catch (Exception ignored) {
        }
    }
}
