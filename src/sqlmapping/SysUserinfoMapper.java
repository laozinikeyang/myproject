package sqlmapping;

import sqlmapping.SysUserinfo;

public interface SysUserinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_userinfo
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int deleteByPrimaryKey(String sysUserid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_userinfo
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int insert(SysUserinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_userinfo
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int insertSelective(SysUserinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_userinfo
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    SysUserinfo selectByPrimaryKey(String sysUserid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_userinfo
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int updateByPrimaryKeySelective(SysUserinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_userinfo
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int updateByPrimaryKey(SysUserinfo record);
}