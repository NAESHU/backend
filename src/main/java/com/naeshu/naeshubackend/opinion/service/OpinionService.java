package com.naeshu.naeshubackend.opinion.service;

import com.naeshu.naeshubackend.announcement.Announcement;
import com.naeshu.naeshubackend.announcement.AnnouncementRepository;
import com.naeshu.naeshubackend.opinion.Opinion;
import com.naeshu.naeshubackend.opinion.dto.request.OpinionRequest;
import com.naeshu.naeshubackend.opinion.repository.OpinionRepository;
import com.naeshu.naeshubackend.user.User;
import com.naeshu.naeshubackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    public void PostOpinion(Long userId, Long announceId, OpinionRequest opinionRequest){
        Announcement announcement = announcementRepository.findById(announceId).get();
        User user = userRepository.findById(userId).get();
        Opinion opinion = new Opinion(
                opinionRequest.content(),
                opinionRequest.createdAt(),
                user,
                announcement
        );
        opinionRepository.save(opinion);
    }
}
