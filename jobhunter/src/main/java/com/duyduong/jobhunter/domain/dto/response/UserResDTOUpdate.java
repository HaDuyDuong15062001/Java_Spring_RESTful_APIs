package com.duyduong.jobhunter.domain.dto.response;

import com.duyduong.jobhunter.constant.myEnum.GenderEnum;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResDTOUpdate {

    Long id;

    String fullName;

    GenderEnum gender;

    int age;

    String address;

    Instant updatedAt;
}
