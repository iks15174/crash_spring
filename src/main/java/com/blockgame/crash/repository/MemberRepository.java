package com.blockgame.crash.repository;

import java.util.List;
import java.util.Optional;

import com.blockgame.crash.model.MemberVo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVo, Long>{

    public Optional<MemberVo> findById(String id);

    public List<MemberVo> findByName(String name);
    
}
