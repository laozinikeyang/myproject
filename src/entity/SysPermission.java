package entity;

public class SysPermission {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_permission.permission_id
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private Integer permissionId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_permission.permission_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private String permissionName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_permission.permission_menu_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private String permissionMenuName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column sys_permission.permission_menu_pid
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	private Integer permissionMenuPid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_permission.permission_id
	 * @return  the value of sys_permission.permission_id
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public Integer getPermissionId() {
		return permissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_permission.permission_id
	 * @param permissionId  the value for sys_permission.permission_id
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_permission.permission_name
	 * @return  the value of sys_permission.permission_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public String getPermissionName() {
		return permissionName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_permission.permission_name
	 * @param permissionName  the value for sys_permission.permission_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_permission.permission_menu_name
	 * @return  the value of sys_permission.permission_menu_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public String getPermissionMenuName() {
		return permissionMenuName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_permission.permission_menu_name
	 * @param permissionMenuName  the value for sys_permission.permission_menu_name
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setPermissionMenuName(String permissionMenuName) {
		this.permissionMenuName = permissionMenuName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column sys_permission.permission_menu_pid
	 * @return  the value of sys_permission.permission_menu_pid
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public Integer getPermissionMenuPid() {
		return permissionMenuPid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column sys_permission.permission_menu_pid
	 * @param permissionMenuPid  the value for sys_permission.permission_menu_pid
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	public void setPermissionMenuPid(Integer permissionMenuPid) {
		this.permissionMenuPid = permissionMenuPid;
	}
}