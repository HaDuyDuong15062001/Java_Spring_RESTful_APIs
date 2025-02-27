package com.duyduong.jobhunter.domain.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResultPaginationDTO {
    MetaDTO meta;
    Object result;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class MetaDTO {
        int page;
        int pageSize;
        int pages;
        long total;
    }
}
