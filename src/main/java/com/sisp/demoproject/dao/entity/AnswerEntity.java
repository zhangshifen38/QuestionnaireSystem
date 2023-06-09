package com.sisp.demoproject.dao.entity;

import java.util.Date;
import java.util.List;

public class AnswerEntity {
    private String id;
    private String questionId;
    private Date creationDate;
    private List<AnswerContentEntity> answerList;

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", creationDate=" + creationDate +
                ", answerList=" + answerList +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<AnswerContentEntity> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerContentEntity> answerList) {
        this.answerList = answerList;
    }
}
