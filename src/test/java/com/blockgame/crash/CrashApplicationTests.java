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
	public void saveRecord() {
		Long score = (long) 24;
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
		System.out.println("START=========================");
		for (int i = 0; i < memberList.size(); i++){
			System.out.println("MEMBER ID : " + memberList.get(i).getId());
			List<RecordVo> records = memberList.get(i).getRecords();
			for(int j = 0; j < records.size(); j++){
				System.out.println("RECORD SCORE : " + records.get(j).getScore());
			}
		}
		System.out.println("FINISH=========================");
	}
	/*
	RECORD를 소유한 MEMBER를 TRANSACTION 안에서 검색했을 때 삭제하고자 하는
	RECORD를 MEMBER LIST에서 삭제해주지 않을 경우 실제로는 삭제되어서 존재하지
	않는 RECORD를 MEMBER가 가지고 있게 된다. 이러한 이유 때문에 JPA를 설계할 때
	한 TRANSACTION안에서 서로 연관관계가 있는 두 ENITIY를 변경할 때 동기화를
	강제하고 있는 것으로 생각된다.
	 */

	@Test
	@Transactional
	public void deleteRecord(){
		Long rcdNo = (long) 1;
		rcdNo = recordRepository.deleteByRcdNo(rcdNo);
		System.out.println("START=========================");
		System.out.println("RcdNo : " + rcdNo);
		List<RecordVo> recordList = recordRepository.findAll();
		for(int i = 0; i < recordList.size(); i++){
			System.out.println("RECORD NO : " + recordList.get(i).getRcdNo());
		}
		System.out.println("FINISH=========================");
	}

	@Test
	@Transactional
	public void deleteMember(){
		String id = "test";
		Long mbr_no = memberRepository.deleteById(id);
		System.out.println("MBR_NO : " + mbr_no);
		this.getMember();
	}

	@Test
	@Transactional
	public void updateMember(){
		String targetId = "test";
		MemberVo memberVo = memberRepository.findById("test");

		String changedId = "testChanged";
		String changedPassword = "12345";
		memberVo.setId(changedId);
		memberVo.setPassword(changedPassword);
		memberRepository.save(memberVo);

		memberVo = memberRepository.findById(changedId);
		System.out.println("START=========================");
		System.out.println("NAME : " + memberVo.getName());
		System.out.println("ID : " + memberVo.getId());
		System.out.println("PASSWORD : " + memberVo.getPassword());
		System.out.println("FINISH=========================");
		
	}

	@Test
	@Transactional
	public void updateRecord(){
		String targetId = "test";
		Long scoreChanged = (long) 1;
		MemberVo memberVo = memberRepository.findById(targetId);
		Long mbrNo = memberVo.getMbrNo();

		List<RecordVo> recordVoList = memberVo.getRecords();
		for(int i = 0; i < recordVoList.size(); i++){
			RecordVo tem = recordVoList.get(i);
			tem.setScore(scoreChanged);
			recordRepository.save(tem);
			scoreChanged = scoreChanged + 1;
		}

		System.out.println(mbrNo);

		recordVoList = recordRepository.findByMember_mbrNo(mbrNo);

		System.out.println("START=========================");
		for(int i = 0; i < recordVoList.size(); i++){
			System.out.println("Member ID : " + recordVoList.get(i).getMember().getId());
			System.out.println("SCORE : " + recordVoList.get(i).getScore());
		}
		System.out.println("FINISH=========================");
	}
}
