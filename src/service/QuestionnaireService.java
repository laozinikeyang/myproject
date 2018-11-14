package service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entity.QuestionnaireMain;

@Service
public class QuestionnaireService {
	@Autowired
	dao.QuestionnaireMainMapper qmm;

	public boolean deleteMainItem(String mainId) {

		return qmm.deleteByPrimaryKey(mainId) == 1 ? true : false;

	}

	public int insertMain(QuestionnaireMain record) {
		int result = qmm.insertSelective(record); // ����Dao��insertSelective����
		return result; // ���result����1��ô֤������ɹ�
	}

	public Long findCount(Map<String, Object> parm) {

		return qmm.selectCount(parm).get("count");

	}

	public List<QuestionnaireMain> findMainPage(Map<String, Object> parm) {

		return qmm.selectPage(parm);

	}
}
