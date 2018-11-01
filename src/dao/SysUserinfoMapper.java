package dao;

import entity.SysUserinfo;

public interface SysUserinfoMapper {
    int deleteByPrimaryKey(String sysUserid);

    int insert(SysUserinfo record);

    int insertSelective(SysUserinfo record);

    SysUserinfo selectByPrimaryKey(String sysUserid);

    int updateByPrimaryKeySelective(SysUserinfo record);

    int updateByPrimaryKey(SysUserinfo record);
}