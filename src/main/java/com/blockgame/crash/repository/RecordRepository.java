package com.blockgame.crash.repository;

import com.blockgame.crash.model.RecordVo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordVo, Long>{
    public Long deleteByRcdNo(Long rcdNo);
}
