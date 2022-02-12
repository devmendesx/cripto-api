package com.criptocloudapi.cloud.repository;

import com.criptocloudapi.cloud.entity.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RevenueRepository extends JpaRepository<Revenue, Long> {
    public Optional<List<Revenue>> findByUser(String findByUser);
}
