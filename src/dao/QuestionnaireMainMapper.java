package dao;

import java.util.List;
import java.util.Map;

import entity.QuestionnaireMain;

public interface QuestionnaireMainMapper {
    int deleteByPrimaryKey(String mainId);

    int insert(QuestionnaireMain record);

    int insertSelective(QuestionnaireMain record);

    QuestionnaireMain selectByPrimaryKey(String mainId);

    int updateByPrimaryKeySelective(QuestionnaireMain record);

    int updateByPrimaryKey(QuestionnaireMain record);
    
    Map<String,Long> selectCount(Map<String,Object> parm);
    
    List<QuestionnaireMain> selectPage(Map<String,Object> parm);
    
    int delCheck(String[] mainIds);
}