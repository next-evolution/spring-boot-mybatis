package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString(doNotUseGetters = true)
public class Account {

    /**
     * アカウントNumber
     */
    private Long accountNumber;

    /**
     * Emailアドレス
     */
    private String emailAddress;

    /**
     * ニックネーム
     */
    private String nickName;

    @JsonIgnore
    private boolean deleteFlag;

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private String createdBy;

    private LocalDateTime updatedAt;

    @JsonIgnore
    private String updatedBy;

}
