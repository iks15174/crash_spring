package com.blockgame.crash;

import com.blockgame.crash.config.auth.PrincipalDetails;
import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.model.RecordVo;
import com.blockgame.crash.repository.MemberRepository;
import com.blockgame.crash.repository.RecordRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
class CrashApplicationTests {

	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void saveScore() {
		Long score = (long) 10;
		MemberVo memberVo = memberRepository.findById("test");
		RecordVo recordVo = new RecordVo();
		recordVo.setScore(score);
		recordVo.setMember(memberVo);
		recordRepository.save(recordVo);
	}
}
