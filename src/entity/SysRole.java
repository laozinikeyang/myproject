package entity;

public class SysRole {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.role_id
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private Integer roleId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.role_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private String roleName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.role_des
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private String roleDes;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_role.role_pid
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private Integer rolePid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.role_id
	 * @return  the value of sys_role.role_id
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.role_id
	 * @param roleId  the value for sys_role.role_id
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.role_name
	 * @return  the value of sys_role.role_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.role_name
	 * @param roleName  the value for sys_role.role_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.role_des
	 * @return  the value of sys_role.role_des
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public String getRoleDes() {
		return roleDes;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.role_des
	 * @param roleDes  the value for sys_role.role_des
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_role.role_pid
	 * @return  the value of sys_role.role_pid
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public Integer getRolePid() {
		return rolePid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_role.role_pid
	 * @param rolePid  the value for sys_role.role_pid
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setRolePid(Integer rolePid) {
		this.rolePid = rolePid;
	}
}