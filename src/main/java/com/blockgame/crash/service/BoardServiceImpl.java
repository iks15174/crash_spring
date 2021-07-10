package com.blockgame.crash.service;

import java.util.List;

import com.blockgame.crash.config.auth.PrincipalDetails;
import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.model.RecordVo;
import com.blockgame.crash.repository.RecordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public void saveScore(Long score) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof PrincipalDetails) {
            PrincipalDetails user = (PrincipalDetails)principal;
            MemberVo memberVo = user.getMemberVo();
            RecordVo recordVo = new RecordVo();
            recordVo.setScore(score);
            recordVo.setMember(memberVo);
            recordRepository.save(recordVo);

        }
    }

    @Override
    public List<RecordVo> getAllRecord() {
        return recordRepository.findAll();
    }

}
