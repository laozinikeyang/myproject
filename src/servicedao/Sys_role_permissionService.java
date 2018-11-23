package servicedao;

import dao.SysRolePermissionMapper;

public interface Sys_role_permissionService extends SysRolePermissionMapper{
	

	public int updateRolPer(String roleName,Integer roleId,String[] splitParm);
	
}
