package com.sisp.demoproject.dao;

import com.sisp.demoproject.dao.entity.OptionEntity;
import com.sisp.demoproject.dao.entity.QuestionEntity;
import com.sisp.demoproject.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionnaireEntityMapper {

    int insert(QuestionnaireEntity questionnaireEntity);

    int insertQuestionnaire(QuestionnaireEntity questionnaireEntity);
    int insertQuestion(QuestionEntity questionEntity);
    int insertOption(OptionEntity optionEntity);
    int deleteQuestionnaireById(QuestionnaireEntity questionnaireEntity);
    int updateQuestionnaireById(QuestionnaireEntity questionnaireEntity);
    int deleteQuestionByQuestionnaireId(@Param("id") String id);
    int deleteOptionByQuestionId(@Param("id") String id);
    List<QuestionnaireEntity> queryQuestionnaireList();
    List<QuestionnaireEntity> selectQuestionnaireByProjectId(@Param("id") String id);

    List<QuestionEntity> queryQuestionListByQuestionnaireId(@Param("id") String id);

    List<OptionEntity> queryOptionListByQuestionId(@Param("id") String id);
    QuestionnaireEntity selectQuestionnaireById(@Param("id") String id);
}
