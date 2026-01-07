package org.example.elementservice.repository;

import org.example.elementservice.model.SimplifiedMountain;
import org.example.elementservice.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrailRepository extends JpaRepository<Trail, UUID> {


    List<Trail> findByMountain(SimplifiedMountain mountain);
}
