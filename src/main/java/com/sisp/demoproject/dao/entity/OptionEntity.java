package com.sisp.demoproject.dao.entity;

public class OptionEntity {
    private String id;
    private String questionId;
    private String optionDescription;
    private String optionScore;

    @Override
    public String toString() {
        return "OptionEntity{" +
                "id='" + id + '\'' +
                ", questionId='" + questionId + '\'' +
                ", optionDescription='" + optionDescription + '\'' +
                ", optionScore='" + optionScore + '\'' +
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

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    public String getOptionScore() {
        return optionScore;
    }

    public void setOptionScore(String optionScore) {
        this.optionScore = optionScore;
    }
}
