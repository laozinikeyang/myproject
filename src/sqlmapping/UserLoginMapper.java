package sqlmapping;

import sqlmapping.UserLogin;
import sqlmapping.UserLoginKey;

public interface UserLoginMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_login
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int deleteByPrimaryKey(UserLoginKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_login
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int insert(UserLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_login
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int insertSelective(UserLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_login
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    UserLogin selectByPrimaryKey(UserLoginKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_login
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int updateByPrimaryKeySelective(UserLogin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_login
     *
     * @mbg.generated Thu Nov 01 10:34:09 CST 2018
     */
    int updateByPrimaryKey(UserLogin record);
}