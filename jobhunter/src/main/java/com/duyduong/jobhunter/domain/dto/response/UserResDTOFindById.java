package com.duyduong.jobhunter.domain.dto.response;

import com.duyduong.jobhunter.constant.myEnum.GenderEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResDTOFindById {

    Long id;

    String fullName;

    String email;

    int age;

    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    Instant createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    Instant updatedAt;
}
