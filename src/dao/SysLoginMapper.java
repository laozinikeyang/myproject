package dao;

import java.util.List;
import java.util.Map;
import entity.SysLogin;

public interface SysLoginMapper {
    int deleteByPrimaryKey(Integer loginId);

    int insert(SysLogin record);

    int insertSelective(SysLogin record);

    SysLogin selectByPrimaryKey(Integer loginId);

    int updateByPrimaryKeySelective(SysLogin record);

    int updateByPrimaryKey(SysLogin record);
    
    Map<String,Long> selectCount();
    
    List<Map<String, Object>> selectLoginRole (Map<String,Object> parm);
    
    int updateSys_login_roleSelective(Map<String,Object> parm);
    
    SysLogin selectByUsername(String username);
}