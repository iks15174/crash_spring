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
    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String role;

    @CreationTimestamp
    private Date createDate;
}
