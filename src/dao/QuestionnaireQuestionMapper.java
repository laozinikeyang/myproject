package dao;

import java.util.List;

import entity.QuestionnaireQuestion;

public interface QuestionnaireQuestionMapper {
    int deleteByPrimaryKey(String questionId);

    int insert(QuestionnaireQuestion record);

    int insertSelective(QuestionnaireQuestion record);

    QuestionnaireQuestion selectByPrimaryKey(String questionId);

    int updateByPrimaryKeySelective(QuestionnaireQuestion record);

    int updateByPrimaryKey(QuestionnaireQuestion record);
    
    List<QuestionnaireQuestion> selectByMainId(String mainId);
}