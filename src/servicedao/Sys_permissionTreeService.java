package servicedao;

import java.util.List;

import dao.Sys_permissionTreeMapper;
import entity.Sys_permissionTree;

public interface Sys_permissionTreeService extends Sys_permissionTreeMapper {

	
	public List<Sys_permissionTree> checked (List<Sys_permissionTree> listParm,String roleName);
	
}
