package dao;

import java.util.List;

import entity.QuestionnaireMsg;

public interface QuestionnaireMsgMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(QuestionnaireMsg record);

    int insertSelective(QuestionnaireMsg record);

    QuestionnaireMsg selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(QuestionnaireMsg record);

    int updateByPrimaryKey(QuestionnaireMsg record);
    
    List<QuestionnaireMsg> selectByMainId(String mainId);
}