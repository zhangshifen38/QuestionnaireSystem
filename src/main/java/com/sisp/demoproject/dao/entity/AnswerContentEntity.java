package com.sisp.demoproject.dao.entity;

public class AnswerContentEntity {
    private String id;
    private String answerId;
    private String optionId;
    private String optionContent;

    @Override
    public String toString() {
        return "AnswerContentEntity{" +
                "id='" + id + '\'' +
                ", answerId='" + answerId + '\'' +
                ", optionId='" + optionId + '\'' +
                ", optionContent='" + optionContent + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }
}
