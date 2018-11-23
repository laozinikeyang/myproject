package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.SysPermissionMapper;
import entity.SysPermission;
import servicedao.Sys_permissionService;

@Service
public class Sys_permissionServiceImpl implements Sys_permissionService {

	@Resource
	SysPermissionMapper PerService;
	
	
	@Override
	public int deleteByPrimaryKey(Integer permissionId) {
		// TODO Auto-generated method stub
		return PerService.deleteByPrimaryKey(permissionId);
	}

	@Override
	public int insert(SysPermission record) {
		// TODO Auto-generated method stub
		return PerService.insert(record);
	}

	@Override
	public int insertSelective(SysPermission record) {
		// TODO Auto-generated method stub
		return PerService.insertSelective(record);
	}

	@Override
	public SysPermission selectByPrimaryKey(Integer permissionId) {
		// TODO Auto-generated method stub
		return PerService.selectByPrimaryKey(permissionId);
	}

	@Override
	public int updateByPrimaryKeySelective(SysPermission record) {
		// TODO Auto-generated method stub
		return PerService.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysPermission record) {
		// TODO Auto-generated method stub
		return PerService.updateByPrimaryKey(record);
	}

}
