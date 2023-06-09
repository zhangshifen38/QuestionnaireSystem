package com.sisp.demoproject.controller;

import com.sisp.demoproject.beans.HttpResponseEntity;
import com.sisp.demoproject.common.utils.UUIDUtil;
import com.sisp.demoproject.dao.entity.AnswerContentEntity;
import com.sisp.demoproject.dao.entity.AnswerEntity;
import com.sisp.demoproject.dao.entity.QuestionEntity;
import com.sisp.demoproject.dao.entity.QuestionnaireEntity;
import com.sisp.demoproject.service.AnswerService;
import com.sisp.demoproject.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @RequestMapping(value = "/insertFullAnswerList",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity insertFullAnswerList(@RequestBody List<AnswerEntity> answerEntityList){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        int res = 0;
        try {
            if(!CollectionUtils.isEmpty(answerEntityList)){
                for(AnswerEntity answerEntity:answerEntityList){
                    answerEntity.setId(UUIDUtil.getOneUUID());
                    answerEntity.setCreationDate(new Date());
                    res += answerService.insertAnswer(answerEntity);
                    if(!CollectionUtils.isEmpty(answerEntity.getAnswerList())){
                        for(AnswerContentEntity answerContentEntity:answerEntity.getAnswerList()){
                            answerContentEntity.setId(UUIDUtil.getOneUUID());
                            answerContentEntity.setAnswerId(answerEntity.getId());
                            answerService.insertAnswerContent(answerContentEntity);
                        }
                    }
                }
            }
            if(res!=0){
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("答卷成功！");
            }else{
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("答卷失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectFullAnswerList", method = RequestMethod.POST, headers = "Accept=application/json")
    public HttpResponseEntity selectFullAnswerList(@RequestBody QuestionnaireEntity questionnaireEntity) {
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        try {
            List<QuestionEntity> questionlist = questionnaireService.queryQuestionListByQuestionnaireId(questionnaireEntity.getId());
            List<AnswerEntity> answerList = null;
            if(!CollectionUtils.isEmpty(questionlist)){
                for(QuestionEntity question:questionlist){
                    List<AnswerEntity> ta = answerService.selectAnswerByQuestionId(question.getId());
                    if(answerList==null){
                        answerList = ta;
                    }else if(ta!=null){
                        answerList.addAll(ta);
                    }
                }
            }
            if(!CollectionUtils.isEmpty(answerList)){
                for(AnswerEntity answer:answerList){
                    answer.setAnswerList(answerService.selectAnswerContentByAnswerId(answer.getId()));
                }
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(answerList);
                httpResponseEntity.setMessage("查询成功！");
            }else{
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(answerList);
                httpResponseEntity.setMessage("查询失败！");
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

}
