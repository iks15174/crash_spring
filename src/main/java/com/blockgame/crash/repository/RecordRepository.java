package com.blockgame.crash.repository;

import java.util.List;

import com.blockgame.crash.model.RecordVo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordVo, Long>{
    public Long deleteByRcdNo(Long rcdNo);
    
    public List<RecordVo> findByMember_mbrNo(Long memberId);
}
