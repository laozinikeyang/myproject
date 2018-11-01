package dao;

import entity.QuestionnaireMainUserKey;

public interface QuestionnaireMainUserMapper {
    int deleteByPrimaryKey(QuestionnaireMainUserKey key);

    int insert(QuestionnaireMainUserKey record);

    int insertSelective(QuestionnaireMainUserKey record);
}