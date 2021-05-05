package com.blockgame.crash.repository;

import java.util.List;

import com.blockgame.crash.model.MemberVo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberVo, Long>{

    public MemberVo findById(String id);

    public boolean existsByName(String name);

    public boolean existsById(String id);
}
