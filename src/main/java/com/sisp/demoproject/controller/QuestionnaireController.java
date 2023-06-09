package com.sisp.demoproject.controller;

import com.sisp.demoproject.beans.HttpResponseEntity;
import com.sisp.demoproject.common.utils.UUIDUtil;
import com.sisp.demoproject.dao.entity.OptionEntity;
import com.sisp.demoproject.dao.entity.QuestionEntity;
import com.sisp.demoproject.dao.entity.QuestionnaireEntity;
import com.sisp.demoproject.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

//    @GetMapping("/answer")
//    public String answerQuestionnaire(@RequestParam(value = "id",required = false) String id, Model model){
//        model.addAttribute("qid",id);
//        return "/pages/answerSheet/index.*";
//    }

    @RequestMapping(value = "/addQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity addQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        questionnaireEntity.setId(UUIDUtil.getOneUUID());
        System.out.println(questionnaireEntity);
        try {
            int res = questionnaireService.addQuestionnaire(questionnaireEntity);
            if(res!=0){
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(questionnaireEntity.getId());
                httpResponseEntity.setMessage("添加成功！");
            }else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData("0");
                httpResponseEntity.setMessage("添加失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectQuestionnaireById",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity selectQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            QuestionnaireEntity res = questionnaireService.selectQuestionnaireById(questionnaireEntity.getId());
            System.out.println(res);
            if(res!=null){
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("查询成功！");
            }else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(0);
                httpResponseEntity.setMessage("查询失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/modifyQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity modifyQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            System.out.println(questionnaireEntity);
            int res=0;
            List<QuestionEntity> oldQuestion=questionnaireService.queryQuestionListByQuestionnaireId(questionnaireEntity.getId());
            if(!CollectionUtils.isEmpty(oldQuestion)){
                for(QuestionEntity question:oldQuestion){
                    res+=questionnaireService.deleteOptionByQuestionId(question.getId());
                }
            }
            res+=questionnaireService.deleteQuestionByQuestionnaireId(questionnaireEntity.getId());
            if(!CollectionUtils.isEmpty(questionnaireEntity.getQuestionList())){
                for(QuestionEntity question:questionnaireEntity.getQuestionList()){
                    question.setId(UUIDUtil.getOneUUID());
                    question.setQuestionnaireId(questionnaireEntity.getId());
                    res+=questionnaireService.insertQuestion(question);
                    if(!CollectionUtils.isEmpty(question.getOption())){
                        for(OptionEntity option:question.getOption()){
                            option.setId(UUIDUtil.getOneUUID());
                            option.setQuestionId(question.getId());
                            res+=questionnaireService.insertOption(option);
                        }
                    }
                }
            }
            res+=questionnaireService.updateQuestionnaireById(questionnaireEntity);
            if(res!=0){
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("更新成功！");
            }else{
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("更新失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectQuestionnaireByProjectId",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity selectQuestionnaireByProjectId(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            List<QuestionnaireEntity> res = questionnaireService.selectQuestionnaireByProjectId(questionnaireEntity.getProjectId());
            System.out.println(res);
            if(!CollectionUtils.isEmpty(res)){
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("查询成功！");
            }else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("查询失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/selectFullQuestionnaireById",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity selectFullQuestionnaireById(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            QuestionnaireEntity res = questionnaireService.selectFullQuestionnaireById(questionnaireEntity.getId());
            if(res!=null){
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("查询成功！");
            }else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("查询失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }

    @RequestMapping(value = "/updateQuestionnaire",method = RequestMethod.POST,headers = "Accept=application/json")
    public HttpResponseEntity updateQuestionnaire(@RequestBody QuestionnaireEntity questionnaireEntity){
        HttpResponseEntity httpResponseEntity=new HttpResponseEntity();
        try {
            int res = questionnaireService.updateQuestionnaireById(questionnaireEntity);
            if(res!=0){
                httpResponseEntity.setCode("200");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("操作成功！");
            }else {
                httpResponseEntity.setCode("500");
                httpResponseEntity.setData(res);
                httpResponseEntity.setMessage("操作失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return httpResponseEntity;
    }
}
