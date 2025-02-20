package com.duyduong.jobhunter.domain.dto.request;

import com.duyduong.jobhunter.constant.myEnum.EError;
import com.duyduong.jobhunter.constant.myEnum.GenderEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserReqDTOUpdate {

    @NotNull(message = "Id " + EError.NOT_NULL)
    Long Id;

    @NotBlank(message = "fullname " + EError.NOT_NULL_OR_EMPTY)
    String fullName;

    @NotNull(message = "gender " + EError.NOT_NULL)
    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @NotNull(message = "age " + EError.NOT_NULL)
    int age;

    @NotBlank(message = "address " + EError.NOT_NULL_OR_EMPTY)
    String address;
}
