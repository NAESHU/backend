package com.naeshu.naeshubackend.opinion;

import com.naeshu.naeshubackend.announcement.Announcement;
import com.naeshu.naeshubackend.common.OpinionStatus;
import com.naeshu.naeshubackend.user.User;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private LocalDate createdAt;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OpinionStatus opinionStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "announcement_id")
    private Announcement announcement;

    public Opinion(String content, LocalDate createdAt, User user, Announcement announcement, OpinionStatus opinionStatus) {
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
        this.announcement = announcement;
        this.opinionStatus = opinionStatus;
    }

    public void selectOpinion() {
        this.opinionStatus = OpinionStatus.YES;
    }
}
