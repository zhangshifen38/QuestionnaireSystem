package com.sisp.demoproject.dao;

import com.sisp.demoproject.dao.entity.AnswerContentEntity;
import com.sisp.demoproject.dao.entity.AnswerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface AnswerEntityMapper {
    int insertAnswer(AnswerEntity answerEntity);
    int insertAnswerContent(AnswerContentEntity answerContentEntity);

    List<AnswerEntity> selectAnswerByQuestionId(@Param("id") String id);

    List<AnswerContentEntity> selectAnswerContentByAnswerId(@Param("id") String id);
}
