package org.example.categoryservice.repository;

import org.example.categoryservice.model.Mountain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MountainRepository extends JpaRepository<Mountain, UUID> {

}
