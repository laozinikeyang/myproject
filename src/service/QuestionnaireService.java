package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import entity.QuestionnaireMain;



@Service
public class QuestionnaireService {
	@Autowired dao.QuestionnaireMainMapper qmm;
	
	public int insertMain (QuestionnaireMain record){
		int result = qmm.insertSelective(record);	//调用Dao层insertSelective方法
		return result;								//如果result等于1那么证明插入成功
	}
	
}
