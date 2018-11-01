package entity;

import java.util.Date;

public class QuestionnaireAnswer {
    private String answerId;

    private Integer answerValue;

    private String answerType;

    private String answerDestype;

    private String answerText;

    private String questionId;

    private Date answerCreat;

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public Integer getAnswerValue() {
        return answerValue;
    }

    public void setAnswerValue(Integer answerValue) {
        this.answerValue = answerValue;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getAnswerDestype() {
        return answerDestype;
    }

    public void setAnswerDestype(String answerDestype) {
        this.answerDestype = answerDestype;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public Date getAnswerCreat() {
        return answerCreat;
    }

    public void setAnswerCreat(Date answerCreat) {
        this.answerCreat = answerCreat;
    }
}