package entity;

import java.util.Date;

public class QuestionnaireQuestion {
    private String questionId;

    private String questionTitle;

    private Date questionCreat;

    private String questionDestype;

    private String questionType;

    private String mainId;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Date getQuestionCreat() {
        return questionCreat;
    }

    public void setQuestionCreat(Date questionCreat) {
        this.questionCreat = questionCreat;
    }

    public String getQuestionDestype() {
        return questionDestype;
    }

    public void setQuestionDestype(String questionDestype) {
        this.questionDestype = questionDestype;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }
}