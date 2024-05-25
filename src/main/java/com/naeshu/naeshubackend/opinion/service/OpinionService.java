package com.naeshu.naeshubackend.opinion.service;

import com.naeshu.naeshubackend.announcement.Announcement;
import com.naeshu.naeshubackend.announcement.AnnouncementRepository;
import com.naeshu.naeshubackend.announcement.AnnouncementResponse;
import com.naeshu.naeshubackend.common.NotFoundException;
import com.naeshu.naeshubackend.common.OpinionStatus;
import com.naeshu.naeshubackend.opinion.Opinion;
import com.naeshu.naeshubackend.opinion.dto.request.OpinionRequest;
import com.naeshu.naeshubackend.opinion.dto.response.OpinionResponse;
import com.naeshu.naeshubackend.opinion.repository.OpinionRepository;
import com.naeshu.naeshubackend.user.User;
import com.naeshu.naeshubackend.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
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
                announcement,
                OpinionStatus.NO
        );
        opinionRepository.save(opinion);
    }

    public List<OpinionResponse> findAllByAnnouncement(Long announceId) {
        Announcement announcement = announcementRepository.findById(announceId)
                .orElseThrow(() -> new NotFoundException("해당 공고글이 존재하지 않습니다."));

        List<Opinion> opinions = opinionRepository.findAllByAnnouncement(announcement);
        return opinions.stream()
                .map(opinion -> new OpinionResponse(opinion.getContent()))
                .toList();
    }

    public void selectOpinion(Long announceId, Long opinionId) {
        Opinion opinion = opinionRepository.findById(opinionId)
                .orElseThrow(() -> new NotFoundException("찾으려는 의견글을 찾을 수 없습니다."));
        opinion.selectOpinion();
        opinionRepository.save(opinion);
    }
}
