package com.naeshu.naeshubackend.company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String companyId;
    private String companyPassword;
    private String companyName;
    private Long companyPoint;

    private Company(String companyId, String companyPassword, String companyName, Long companyPoint) {
        this.companyId = companyId;
        this.companyPassword = companyPassword;
        this.companyName = companyName;
        this.companyPoint = companyPoint;
    }

    public static Company of(String companyId, String companyPassword, String companyName) {
        return new Company(companyId, companyPassword, companyName,0);
    }
}
