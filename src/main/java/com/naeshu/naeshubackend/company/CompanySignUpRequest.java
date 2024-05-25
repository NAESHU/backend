package com.naeshu.naeshubackend.company;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompanySignUpRequest {

    private String companyName;
    private String companyId;
    private String companyPassword;

    public CompanySignUpRequest(String companyName, String companyId, String companyPassword) {
        this.companyName = companyName;
        this.companyId = companyId;
        this.companyPassword = companyPassword;
    }
}
