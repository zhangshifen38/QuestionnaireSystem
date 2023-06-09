package com.sisp.demoproject.service;

import com.sisp.demoproject.dao.AnswerEntityMapper;
import com.sisp.demoproject.dao.entity.AnswerContentEntity;
import com.sisp.demoproject.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerEntityMapper answerEntityMapper;

    public int insertAnswer(AnswerEntity answerEntity){
        return answerEntityMapper.insertAnswer(answerEntity);
    }
    public int insertAnswerContent(AnswerContentEntity answerContentEntity){
        return answerEntityMapper.insertAnswerContent(answerContentEntity);
    }

    public List<AnswerEntity> selectAnswerByQuestionId(String id){
        return answerEntityMapper.selectAnswerByQuestionId(id);
    }

    public List<AnswerContentEntity> selectAnswerContentByAnswerId(String id){
        return answerEntityMapper.selectAnswerContentByAnswerId(id);
    }

}
