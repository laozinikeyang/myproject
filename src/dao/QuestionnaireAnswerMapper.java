package dao;

import java.util.List;

import entity.QuestionnaireAnswer;

public interface QuestionnaireAnswerMapper {
    int deleteByPrimaryKey(String answerId);
    void deleteAll(String questionid);
    int insert(QuestionnaireAnswer record);

    int insertSelective(QuestionnaireAnswer record);

    QuestionnaireAnswer selectByPrimaryKey(String answerId);

    int updateByPrimaryKeySelective(QuestionnaireAnswer record);

    int updateByPrimaryKey(QuestionnaireAnswer record);
    
    List<QuestionnaireAnswer> selectByQuestionId(String[] questionIds);
}