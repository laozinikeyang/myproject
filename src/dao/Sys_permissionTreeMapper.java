package dao;

import java.util.List;

import entity.Sys_permissionTree;

public interface Sys_permissionTreeMapper {
    
	List<Sys_permissionTree> selectAll();
	
	int deleteByPrimaryKey(Integer id);
	
}