package com.duyduong.jobhunter.domain.dto.response;

import com.duyduong.jobhunter.constant.myEnum.GenderEnum;
import com.duyduong.jobhunter.domain.user.Company;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResDTOCreate {

    Long id;

    String name;

    String email;

    int age;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    String address;

    Instant createdAt;

    CompanyUser companyUser;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class CompanyUser {
        Long Id;
        String name;
    }
}
