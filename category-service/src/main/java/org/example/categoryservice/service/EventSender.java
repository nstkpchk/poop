package org.example.categoryservice.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Service
public class EventSender {
    private final RestTemplate rest = new RestTemplate();

    public void sendMountainDeleted(UUID id) {
        try {
            rest.delete("http://localhost:8082/internal/mountains/" + id);
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
            rest.postForObject("http://localhost:8082/internal/mountains", body, Void.class);
        } catch (Exception ignored) {
        }
    }
}
