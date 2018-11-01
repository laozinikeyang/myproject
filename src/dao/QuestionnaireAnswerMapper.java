package dao;

import entity.QuestionnaireAnswer;

public interface QuestionnaireAnswerMapper {
    int deleteByPrimaryKey(String answerId);

    int insert(QuestionnaireAnswer record);

    int insertSelective(QuestionnaireAnswer record);

    QuestionnaireAnswer selectByPrimaryKey(String answerId);

    int updateByPrimaryKeySelective(QuestionnaireAnswer record);

    int updateByPrimaryKey(QuestionnaireAnswer record);
}