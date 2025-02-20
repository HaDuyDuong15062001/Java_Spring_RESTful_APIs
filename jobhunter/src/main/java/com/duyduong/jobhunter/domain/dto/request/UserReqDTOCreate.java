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
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class UserReqDTOCreate {

    @NotBlank(message = EError.NOT_NULL_OR_EMPTY)
    String fullName;

    @NotBlank(message = EError.NOT_NULL_OR_EMPTY)
    String email;

    @NotBlank(message = EError.NOT_NULL_OR_EMPTY)
    String password;

    @NotNull(message = EError.NOT_NULL)
    int age;

    @NotNull(message = EError.NOT_NULL)
    @Enumerated(EnumType.STRING)
    GenderEnum gender;

    @NotBlank(message = EError.NOT_NULL_OR_EMPTY)
    String address;
}
