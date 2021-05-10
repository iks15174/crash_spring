package com.blockgame.crash.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name="record")
public class RecordVo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rcdNo;

    private Long score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberVo member;
}
