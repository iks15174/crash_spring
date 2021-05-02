package com.blockgame.crash.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
