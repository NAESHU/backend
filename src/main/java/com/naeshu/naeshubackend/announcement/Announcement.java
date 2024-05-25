package com.naeshu.naeshubackend.announcement;

import com.naeshu.naeshubackend.common.BidStatus;
import com.naeshu.naeshubackend.company.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class Announcement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private Long opinionPrice;
    private Long commentPrice;
    private LocalDate createdAt;
    private LocalDate deadline;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private BidStatus bidStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Announcement() {
    }

    @Builder
    private Announcement(Long id, String title, String content, Long opinionPrice, Long commentPrice, LocalDate createdAt, LocalDate deadline, BidStatus bidStatus, Company company) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.opinionPrice = opinionPrice;
        this.commentPrice = commentPrice;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.bidStatus = bidStatus;
        this.company = company;
    }
}
