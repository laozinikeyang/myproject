package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.QuestionnaireAnswerMapper;
import dao.QuestionnaireMsgMapper;
import dao.QuestionnaireQuestionMapper;
import entity.QuestionnaireAnswer;
import entity.QuestionnaireMain;
import entity.QuestionnaireMsg;
import entity.QuestionnaireQuestion;

@Service
public class QuestionnaireService {
	@Autowired
	QuestionnaireMsgMapper msg;
	@Autowired
	QuestionnaireQuestionMapper qqm;
	@Autowired
	dao.QuestionnaireMainMapper qmm;
	@Autowired
	QuestionnaireAnswerMapper qam;
	
	
	public Map<String,Object> selectQuestionnaire(String mainId,boolean isStatistic){
		Map<String,Object> questionnaire  = new HashMap<String, Object>();  //����ģ��
		QuestionnaireMain qm = qmm.selectByPrimaryKey(mainId);	//����mainId��ȡ��������
		//����mainId��ȡ�������
		List<QuestionnaireQuestion> questions = qqm.selectByMainId(mainId);
		//����һ��������������ڱ����id��Ϊ�˲�ѯ��
		String[] questionIds = new String[questions.size()];
		//�����������ݣ����questionIds����
		int i = 0;
		for (QuestionnaireQuestion entity: questions){
			questionIds[i] = entity.getQuestionId();
			i++;
		}
		//����һ��List����ȡ��
		List<QuestionnaireAnswer> answers = new ArrayList<>();
		if (questions.size()>0){
			answers = qam.selectByQuestionId(questionIds);
		}
		//���������Ϊ��ʵ�ְٷֱȣ����Ի�ȡһ�����ѡ����
		Map<String,Integer> counts = new HashMap<>();
		for (QuestionnaireQuestion entity: questions){
			String questionId = entity.getQuestionId();
			Integer count = 0;
			for (QuestionnaireAnswer answer :answers){
				if (questionId.equals(answer.getQuestionId())){
					count += answer.getAnswerValue();
				}
			}
			counts.put(entity.getQuestionId(), count);
		}
		//�����ͳ�ƽ������ô���ǻ�ȡquestionnaire_msg����
		if (isStatistic){
			List<QuestionnaireMsg> msgs=msg.selectByMainId(mainId);
			questionnaire.put("msgs",msgs);
		}
		questionnaire.put("main", qm);				//ģ�͵��д���������Ϣ
		questionnaire.put("question", questions);	//ģ�͵��д���������Ϣ
		questionnaire.put("answer", answers);		//ģ�͵��д������Ϣ
		questionnaire.put("counts", counts);		//ģ�͵��д��������´�ѡ��������Ϣ
		
		return questionnaire;
	}
	
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

	public boolean deleteCheck(String[] mainIds) {

		return qmm.delCheck(mainIds) >= 1 ? true : false;

	}

	public boolean updateMainAction(QuestionnaireMain record) {

		if (qmm.updateByPrimaryKeySelective(record) == 1) {
			return true;
		}

		return false;
	}
	
	public boolean updateCopyQuestion (String mainId,String username){
		QuestionnaireMain quest = qmm.selectByPrimaryKey(mainId);	//����Id��ȡ��������
		if (quest!=null){
			String newMainId = UUID.randomUUID().toString();		//�½�һ������Id
			quest.setMainId(newMainId);								//��������Id
			quest.setMainIsuse("n");								//���÷���״̬
			quest.setMainCreat(new Date());							//���ô���ʱ��
			quest.setMainTitle(quest.getMainTitle()+"(����)");		//���ÿ�������
			quest.setMainCreatuser(username);						//���ô�����
			qmm.insertSelective(quest);								//����������
			//����mainId��ȡ��������
			List<QuestionnaireQuestion> oldQuestions = qqm.selectByMainId(mainId);
			if (oldQuestions!=null){
				//�����������ݣ���ѭ�������µ�����
				for (QuestionnaireQuestion oldQuestion : oldQuestions){
					//��ȡ��������Id
					String oldQuestionId = oldQuestion.getQuestionId();
					//�½�һ������Id
					String newQueationId =  UUID.randomUUID().toString();
					//��ԭ���ݵ�Id���ó�������Id
					oldQuestion.setQuestionId(newQueationId);
					//��Դ���ݵ�����Id�޸ĳ��������Id
					oldQuestion.setMainId(newMainId);
					//�����������ʹ�õ��ǻ�ȡ����ģ��
					qqm.insert(oldQuestion);
					//����Id��ȡ��
					List<QuestionnaireAnswer> oldAnswers = 
							qam.selectByQuestionId(new String[]{oldQuestionId});
					//�жϴ��Ƿ�Ϊ��
					if (oldAnswers != null){
						//�������ݣ���ѭ�������µĴ�
						for (QuestionnaireAnswer oldAnswer : oldAnswers){
							//����һ���µĴ�Id
							String newAnswerId = UUID.randomUUID().toString();
							//�����µ�Id
							oldAnswer.setAnswerId(newAnswerId);
							//�����µ�����Id
							oldAnswer.setQuestionId(newQueationId);
							//��ʼ��ѡ�����
							oldAnswer.setAnswerValue(0);
							//��������
							qam.insert(oldAnswer);
						}
					}
				}
			}
		}else{
			return false;
		}
		return true;
	}
	
}
