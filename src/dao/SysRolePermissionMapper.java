package dao;

import java.util.List;

import entity.SysRolePermission;

public interface SysRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);

    SysRolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRolePermission record);

    int updateByPrimaryKey(SysRolePermission record);
    
    int deleteByRoleName (String roleName);
    
    List<SysRolePermission> selectByRoleName(String roleName);
    
    
}