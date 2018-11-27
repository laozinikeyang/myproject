package service;

import java.util.List;
import java.util.Map;

import dao.SysLoginMapper;

public interface Sys_loginService extends SysLoginMapper {

	List<Map<String, Object>> selectLoginRole (Map<String,Object> parm);
	
}
