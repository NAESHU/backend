package com.naeshu.naeshubackend.announcement;

import com.naeshu.naeshubackend.common.BidStatus;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import com.naeshu.naeshubackend.company.Company;
import com.naeshu.naeshubackend.company.CompanyRepository;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;
    private final CompanyRepository companyRepository;

    public void create(Long companyId, String title, String content, Long opinionPrice, Long commentPrice, LocalDate deadline) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new UnAuthorizedException("존재하지 않는 기업 사용자입니다."));

        Announcement announcement = Announcement.builder()
                .title(title)
                .content(content)
                .opinionPrice(opinionPrice)
                .commentPrice(commentPrice)
                .createdAt(LocalDate.now())
                .deadline(deadline)
                .bidStatus(BidStatus.NOT_SOLD)
                .company(company)
                .build();

        announcementRepository.save(announcement);
    }

    public List<AnnouncementResponse> findAll() {
        List<Announcement> announcements = announcementRepository.findAll();
        return announcements.stream()
                .map((announcement -> new AnnouncementResponse(
                        announcement.getId(),
                        announcement.getTitle(),
                        announcement.getContent(),
                        announcement.getOpinionPrice(),
                        announcement.getCommentPrice(),
                        announcement.getCreatedAt(),
                        announcement.getDeadline(),
                        announcement.getBidStatus(),
                        announcement.getCompany().getId()
                )))
                .toList();
    }
}
