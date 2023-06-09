package com.sisp.demoproject.service;

import com.sisp.demoproject.common.utils.UUIDUtil;
import com.sisp.demoproject.dao.QuestionnaireEntityMapper;
import com.sisp.demoproject.dao.entity.OptionEntity;
import com.sisp.demoproject.dao.entity.QuestionEntity;
import com.sisp.demoproject.dao.entity.QuestionnaireEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireEntityMapper questionnaireEntityMapper;

    public int addQuestionnaire(QuestionnaireEntity questionnaireEntity){
        int res = questionnaireEntityMapper.insertQuestionnaire(questionnaireEntity);
        return res;
    }

    public QuestionnaireEntity selectQuestionnaireById(String id){
        QuestionnaireEntity res = questionnaireEntityMapper.selectQuestionnaireById(id);
        return res;
    }

    public List<QuestionEntity> queryQuestionListByQuestionnaireId( String id){
        List<QuestionEntity> ret = questionnaireEntityMapper.queryQuestionListByQuestionnaireId(id);
        return ret;
    }
    public int deleteOptionByQuestionId( String id){
        int ret=questionnaireEntityMapper.deleteOptionByQuestionId(id);
        return ret;
    }

    public int deleteQuestionByQuestionnaireId(String id){
        int ret=questionnaireEntityMapper.deleteQuestionByQuestionnaireId(id);
        return ret;
    }

    public int insertQuestion(QuestionEntity questionEntity){
        return questionnaireEntityMapper.insertQuestion(questionEntity);
    }
    public int insertOption(OptionEntity optionEntity){
        return questionnaireEntityMapper.insertOption(optionEntity);
    }
    public int updateQuestionnaireById(QuestionnaireEntity questionnaireEntity){
        return questionnaireEntityMapper.updateQuestionnaireById(questionnaireEntity);
    }
    public List<QuestionnaireEntity> selectQuestionnaireByProjectId(String id){
        return questionnaireEntityMapper.selectQuestionnaireByProjectId(id);
    }
    public QuestionnaireEntity selectFullQuestionnaireById(String id){
        QuestionnaireEntity ret=questionnaireEntityMapper.selectQuestionnaireById(id);
        if(ret==null){
            return null;
        }
        ret.setQuestionList(questionnaireEntityMapper.queryQuestionListByQuestionnaireId(ret.getId()));
        if(!CollectionUtils.isEmpty(ret.getQuestionList())) {
            for (QuestionEntity question : ret.getQuestionList()) {
                question.setOption(questionnaireEntityMapper.queryOptionListByQuestionId(question.getId()));
            }
        }
        return ret;
    }
}
