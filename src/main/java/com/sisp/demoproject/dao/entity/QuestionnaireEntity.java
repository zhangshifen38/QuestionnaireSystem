package com.sisp.demoproject.dao.entity;

import java.util.Date;
import java.util.List;

public class QuestionnaireEntity {
    private String id;
    private String projectId;
    private String status;
    private String questionnaireName;
    private String questionnaireContent;
    private String questionnaireType;
    private List<QuestionEntity> questionList;
    private Date startTime;
    private Date stopTime;
    private String createdBy;
    private Date creationDate;
    private String lastUpdatedBy;
    private Date lastUpdateDate;

    @Override
    public String toString() {
        return "QuestionnaireEntity{" +
                "id='" + id + '\'' +
                ", projectId='" + projectId + '\'' +
                ", status='" + status + '\'' +
                ", questionnaireName='" + questionnaireName + '\'' +
                ", questionnaireContent='" + questionnaireContent + '\'' +
                ", questionnaireType='" + questionnaireType + '\'' +
                ", questionList=" + questionList +
                ", startTime=" + startTime +
                ", stopTime=" + stopTime +
                ", createdBy='" + createdBy + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getQuestionnaireContent() {
        return questionnaireContent;
    }

    public void setQuestionnaireContent(String questionnaireContent) {
        this.questionnaireContent = questionnaireContent;
    }

    public String getQuestionnaireType() {
        return questionnaireType;
    }

    public void setQuestionnaireType(String questionnaireType) {
        this.questionnaireType = questionnaireType;
    }

    public List<QuestionEntity> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionEntity> questionList) {
        this.questionList = questionList;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
