package com.naeshu.naeshubackend.company;

import lombok.Getter;

public record CompanyLoginRequest(
    String companyId, String companyPassword
) {
}
