package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import entity.QuestionnaireMain;



@Service
public class QuestionnaireService {
	@Autowired dao.QuestionnaireMainMapper qmm;
	
	public int insertMain (QuestionnaireMain record){
		int result = qmm.insertSelective(record);	//����Dao��insertSelective����
		return result;								//���result����1��ô֤������ɹ�
	}
	
}
