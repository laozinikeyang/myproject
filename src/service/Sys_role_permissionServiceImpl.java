package service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.SysRolePermissionMapper;
import entity.SysRolePermission;
import servicedao.Sys_role_permissionService;
import utils.StringUtils;

@Service
public class Sys_role_permissionServiceImpl implements Sys_role_permissionService {

	
	@Resource
	SysRolePermissionMapper rolperService;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return rolperService.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SysRolePermission record) {
		
		// TODO Auto-generated method stub
		return rolperService.insert(record);
	}

	@Override
	public int insertSelective(SysRolePermission record) {
		// TODO Auto-generated method stub
		return rolperService.insertSelective(record);
	}

	@Override
	public SysRolePermission selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return rolperService.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysRolePermission record) {
		// TODO Auto-generated method stub
		return rolperService.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysRolePermission record) {
		// TODO Auto-generated method stub
		return rolperService.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return rolperService.deleteByRoleName(roleName);
	}

	@Override
	public int updateRolPer(String roleName, Integer roleId, String[] splitParm) {
		// TODO Auto-generated method stub
		int i = this.deleteByRoleName(roleName);
		int resultInt = 0;
		for (String parm:splitParm){
			if(StringUtils.isNumeric(parm)){
				Integer permissionId = Integer.parseInt(parm);
				SysRolePermission srp = new SysRolePermission();
				srp.setPermissionId(permissionId);
				srp.setRoleId(roleId);
				srp.setRoleName(roleName);
				resultInt += this.insertSelective(srp);
			}
		}
		
		return resultInt;
	}

	@Override
	public List<SysRolePermission> selectByRoleName(String roleName) {
		// TODO Auto-generated method stub
		return rolperService.selectByRoleName(roleName);
	}

}
