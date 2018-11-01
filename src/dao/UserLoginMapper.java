package dao;

import entity.UserLogin;
import entity.UserLoginKey;

public interface UserLoginMapper {
    int deleteByPrimaryKey(UserLoginKey key);

    int insert(UserLogin record);

    int insertSelective(UserLogin record);

    UserLogin selectByPrimaryKey(UserLoginKey key);

    int updateByPrimaryKeySelective(UserLogin record);

    int updateByPrimaryKey(UserLogin record);
}