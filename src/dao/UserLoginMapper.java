package dao;

import entity.UserLogin;
import entity.UserLoginKey;

public interface UserLoginMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_login
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int deleteByPrimaryKey(UserLoginKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_login
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int insert(UserLogin record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_login
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int insertSelective(UserLogin record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_login
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	UserLogin selectByPrimaryKey(UserLoginKey key);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_login
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int updateByPrimaryKeySelective(UserLogin record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table user_login
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int updateByPrimaryKey(UserLogin record);
}