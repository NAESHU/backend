package com.naeshu.naeshubackend.opinion.repository;

import com.naeshu.naeshubackend.opinion.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
}
