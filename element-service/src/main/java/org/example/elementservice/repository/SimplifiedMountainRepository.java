package org.example.elementservice.repository;

import org.example.elementservice.model.SimplifiedMountain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SimplifiedMountainRepository extends JpaRepository<SimplifiedMountain, UUID> {

}
