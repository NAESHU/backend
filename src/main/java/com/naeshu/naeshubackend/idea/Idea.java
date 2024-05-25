package com.naeshu.naeshubackend.idea;

import com.naeshu.naeshubackend.category.Category;
import com.naeshu.naeshubackend.common.BidStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Idea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private LocalDate createdAt;
    private LocalDate deadline;
    private Long startPrice;
    private BidStatus bidStatus;
    private Long currentPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
