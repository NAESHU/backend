package com.naeshu.naeshubackend.opinion.service;

import com.naeshu.naeshubackend.announcement.Announcement;
import com.naeshu.naeshubackend.announcement.AnnouncementRepository;
import com.naeshu.naeshubackend.common.NotFoundException;
import com.naeshu.naeshubackend.opinion.Opinion;
import com.naeshu.naeshubackend.opinion.dto.request.OpinionRequest;
import com.naeshu.naeshubackend.opinion.repository.OpinionRepository;
import com.naeshu.naeshubackend.user.User;
import com.naeshu.naeshubackend.user.repository.UserRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    public void create(Long userId, Long announceId, String content) {
        Announcement announcement = announcementRepository.findById(announceId)
                .orElseThrow(() -> new NotFoundException("해당 공고글이 존재하지 않습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("해당 유저가 존재하지 않습니다."));
        Opinion opinion = new Opinion(
                content,
                LocalDate.now(),
                user,
                announcement
        );
        opinionRepository.save(opinion);
    }
}
