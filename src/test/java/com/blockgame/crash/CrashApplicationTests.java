package com.blockgame.crash;

import com.blockgame.crash.model.MemberVo;
import com.blockgame.crash.model.RecordVo;
import com.blockgame.crash.repository.MemberRepository;
import com.blockgame.crash.repository.RecordRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import javax.transaction.Transactional;

@SpringBootTest
class CrashApplicationTests {

	@Autowired
	private RecordRepository recordRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void saveScore() {
		Long score = (long) 5;
		MemberVo memberVo = memberRepository.findById("test");
		RecordVo recordVo = new RecordVo();
		recordVo.setScore(score);
		recordVo.setMember(memberVo);
		recordRepository.save(recordVo);
	}

	@Test
	public void getRecord(){
		List<RecordVo> recordList = recordRepository.findAll();
		for (int i = 0; i < recordList.size(); i++){
			System.out.println("=========================");
			System.out.println(recordList.get(i).getRcdNo());
			System.out.println("=========================");
		}
	}

	@Test
	@Transactional
	public void getMember(){
		List<MemberVo> memberList = memberRepository.findAll();
		for (int i = 0; i < memberList.size(); i++){
			System.out.println("START=========================");
			MemberVo temp = memberList.get(i);
			System.out.println(memberList.get(i).getId());
			List<RecordVo> records = memberList.get(i).getRecords();
			for(int j = 0; j < records.size(); j++){
				System.out.println(records.get(j).getScore());
			}
			System.out.println("FINISH=========================");
		}
	}
}
