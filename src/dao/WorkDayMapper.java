package dao;

import entity.WorkDay;

public interface WorkDayMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table work_day
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int deleteByPrimaryKey(String workId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table work_day
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int insert(WorkDay record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table work_day
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int insertSelective(WorkDay record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table work_day
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	WorkDay selectByPrimaryKey(String workId);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table work_day
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int updateByPrimaryKeySelective(WorkDay record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table work_day
	 * @mbg.generated  Thu Nov 01 10:56:55 CST 2018
	 */
	int updateByPrimaryKey(WorkDay record);
}