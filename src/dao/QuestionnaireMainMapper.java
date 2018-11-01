package dao;

import entity.QuestionnaireMain;

public interface QuestionnaireMainMapper {
    int deleteByPrimaryKey(String mainId);

    int insert(QuestionnaireMain record);

    int insertSelective(QuestionnaireMain record);

    QuestionnaireMain selectByPrimaryKey(String mainId);

    int updateByPrimaryKeySelective(QuestionnaireMain record);

    int updateByPrimaryKey(QuestionnaireMain record);
}