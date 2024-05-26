package com.naeshu.naeshubackend.comment;

import com.naeshu.naeshubackend.common.CommentStatus;
import com.naeshu.naeshubackend.opinion.Opinion;
import com.naeshu.naeshubackend.user.User;
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
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    private CommentStatus commentStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opinion_id")
    private Opinion opinion;

    public Comment(String content, User user, Opinion opinion, LocalDate createdAt, CommentStatus commentStatus) {
        this.content = content;
        this.user = user;
        this.opinion = opinion;
        this.createdAt = createdAt;
        this.commentStatus = commentStatus;
    }
    public void updateStatus(CommentStatus commentStatus){
        this.commentStatus = commentStatus;
    }
}
