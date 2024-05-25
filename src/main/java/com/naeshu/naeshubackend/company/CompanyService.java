package com.naeshu.naeshubackend.company;

import com.naeshu.naeshubackend.common.ConflictException;
import com.naeshu.naeshubackend.common.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public void signup(String companyName, String companyId, String companyPassword) {
        companyRepository.findByCompanyId(companyId)
                .ifPresent(it -> {
                    throw new ConflictException("해당 아이디로 가입한 기업 아이디가 있습니다.");
                });
        Company company = Company.of(companyId, companyPassword, companyName);
        companyRepository.save(company);
    }

    public Long login(String companyId, String companyPassword) {
        Company company = companyRepository.findByCompanyId(companyId)
                .orElseThrow(() -> new UnAuthorizedException("존재하지 않은 아이디입니다."));
        company.login(companyPassword);
        return company.getId();
    }
}
