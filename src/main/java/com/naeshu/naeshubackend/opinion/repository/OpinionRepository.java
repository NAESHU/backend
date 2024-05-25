package com.naeshu.naeshubackend.opinion.repository;

import com.naeshu.naeshubackend.announcement.Announcement;
import com.naeshu.naeshubackend.opinion.Opinion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

    List<Opinion> findAllByAnnouncement(Announcement announcement);
}
