package com.duyduong.jobhunter.domain;

import com.duyduong.jobhunter.util.SecurityUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.Instant;

@Entity
@Table(name = "companies")
@Getter
@Setter
@RequiredArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "name không thể để trống!!!")
    private String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss a", timezone = "GMT+7")
    private Instant createdAt;

    private Instant updatedAt;

    private String createdBy;

    private String updatedBy;

    private String logo;

    @PrePersist
    public void handleBeforeCreateAt() {
        this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() == true
                ? SecurityUtil.getCurrentUserLogin().get()
                : "";

        this.createdAt = Instant.now();
    }
}
