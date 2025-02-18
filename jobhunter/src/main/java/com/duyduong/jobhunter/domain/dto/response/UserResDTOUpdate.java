package com.duyduong.jobhunter.domain.dto.response;

import com.duyduong.jobhunter.myEnum.EError;
import com.duyduong.jobhunter.myEnum.GenderEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResDTOUpdate {

    Long Id;

    String fullName;

    GenderEnum gender;

    int age;

    String address;

    Instant updatedAt;
}
