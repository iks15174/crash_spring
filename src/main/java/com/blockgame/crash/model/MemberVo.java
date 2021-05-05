package com.blockgame.crash.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.CreationTimestamp;

import lombok.*;

@Data
@Entity(name="member")
public class MemberVo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbrNo;

    @Column(unique = true)
    @NotBlank
    private String id;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    //@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{4,20}",
    //        message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @Pattern(regexp="(?=.*[0-9]).{4,20}",
            message = "비밀번호는 숫자가 4자 ~ 20자의 비밀번호여야 합니다.")
    private String password;

    @NotBlank
    private String role;

    @CreationTimestamp
    private Date createDate;
}
