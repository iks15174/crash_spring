package com.blockgame.crash.service;

import java.util.List;

import com.blockgame.crash.model.RecordVo;

public interface BoardService {
    public void saveScore(Long score);

    public List<RecordVo> getAllRecord();
}
