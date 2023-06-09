package com.sisp.demoproject.dao.entity;

import java.util.List;

public class QuestionEntity {
    private String id;
    private String questionnaireId;
    private int questionType;
    private String questionName;
    private String leftTitle;
    private List<OptionEntity> option;
    private boolean requireFlag;

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id='" + id + '\'' +
                ", questionnaireId='" + questionnaireId + '\'' +
                ", questionType=" + questionType +
                ", questionName='" + questionName + '\'' +
                ", leftTitle='" + leftTitle + '\'' +
                ", option=" + option +
                ", requireFlag=" + requireFlag +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public List<OptionEntity> getOption() {
        return option;
    }

    public void setOption(List<OptionEntity> option) {
        this.option = option;
    }

    public boolean isRequireFlag() {
        return requireFlag;
    }

    public void setRequireFlag(boolean requireFlag) {
        this.requireFlag = requireFlag;
    }
}
