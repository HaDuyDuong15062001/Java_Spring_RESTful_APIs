package com.duyduong.jobhunter.domain.dto.response;

import com.duyduong.jobhunter.myEnum.GenderEnum;
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

    String fullName;

    String email;

    int age;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    String address;

    Instant createdAt;
}
