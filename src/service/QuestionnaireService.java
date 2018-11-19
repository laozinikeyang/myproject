package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.QuestionnaireAnswerMapper;
import dao.QuestionnaireMsgMapper;
import dao.QuestionnaireQuestionMapper;
import entity.QuestionnaireAnswer;
import entity.QuestionnaireMain;
import entity.QuestionnaireMainUserKey;
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
	@Autowired
	SimpleDateFormat sdf;
	
public boolean updateValueIn(List<String> answerIds,String userId,String mainId,String message,String wxname){
		
	QuestionnaireMainUserKey qmuEntity = new QuestionnaireMainUserKey();
		qmuEntity.setMainId(mainId);
		qmuEntity.setUserId(userId);
	
		if (message != null && !"".equals(message)){
			QuestionnaireMsg msgEntity = new QuestionnaireMsg();
			msgEntity.setMsgId(UUID.randomUUID().toString());
			msgEntity.setMainId(mainId);
			msgEntity.setMsgText(message);
			msgEntity.setMsgCreatuser(wxname);
			msgEntity.setMsgCreattime(new Date());
			msg.insert(msgEntity);
		}
		
//		return qam.updateValueIn(answerIds)>=0&&qmum.insert(qmuEntity)==1&&qmm.updateMainCreat(mainId)==1;
		return true;
	}

	public boolean delectQuestionAndAnswer(String questionId) {

		boolean bflag = qqm.deleteByPrimaryKey(questionId) == 1;

		return bflag;

	}

	public boolean updateQuestionAndAnswers(String mainId, Map<String, String> filePathMap,
			Map<String, String[]> answertext) {
		boolean bflag = true;
		String questionId = answertext.get("questionId")[0];
//		System.out.println("questionId"+questionId);
		
		QuestionnaireQuestion qqEntity = new QuestionnaireQuestion();
		qqEntity.setQuestionId(questionId);
		qqEntity.setQuestionTitle(answertext.get("questionTitle")[0]);
		qqEntity.setQuestionType(answertext.get("questionType")[0]);
		qam.deleteAll(questionId);
		if (qqm.updateByPrimaryKeySelective(qqEntity) != 1) {
			return false;
		}

		answertext.remove("mainId");
		answertext.remove("questionId");
		answertext.remove("questionTitle");
		answertext.remove("questionType");
		Set<Entry<String, String[]>> answerEntry = answertext.entrySet();

		for (Entry<String, String[]> tempTest : answerEntry) {
			String text = tempTest.getValue()[0];
			if (text != null && !"".equals(text.trim())) {
				QuestionnaireAnswer qaEntity = new QuestionnaireAnswer();
				String filePath = filePathMap.get(tempTest.getKey());
				if (filePath != null && !"".equals(filePath.trim())) {
					qaEntity.setAnswerText(filePath);
					qaEntity.setAnswerType("y");
					qaEntity.setAnswerDestype(tempTest.getValue()[0] + "(������ظ���)");
				} else {
					qaEntity.setAnswerText("#");
					qaEntity.setAnswerType("n");
					qaEntity.setAnswerDestype(tempTest.getValue()[0]);
				}
				qaEntity.setAnswerCreat(new Date());
				qaEntity.setAnswerId(tempTest.getKey());
				/**
				 * ���´�������������ͬ����֮ͬ��������������һ���жϣ����ȸ���Id���´��룬������²��ɹ�����в��� ������
				 */
				
				int result = qam.updateByPrimaryKeySelective(qaEntity);
				if (result != 1) {
					qaEntity.setAnswerValue(0);
					qaEntity.setQuestionId(questionId);
					qam.insert(qaEntity);
				}
			}
		}

		return bflag;
	}

	public Boolean updateMainTitle(String mainId, String mainTitle, String mainEndtime) {

		if (mainId != null && mainTitle != null && !"".equals(mainId.trim()) && !"".equals(mainTitle.trim())) {
			QuestionnaireMain qm = new QuestionnaireMain();
			qm.setMainId(mainId);
			qm.setMainTitle(mainTitle);
			try {
				if (mainEndtime != null) {
					qm.setMainEndtime(sdf.parse(mainEndtime));
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			return qmm.updateByPrimaryKeySelective(qm) == 1;
		} else {
			return false;
		}
	}

	public Map<String, Object> selectQuestionByIdToAnswer(String questionId) {
System.out.println("serivce");
		Map<String, Object> map = new HashMap<>();
		QuestionnaireQuestion question = qqm.selectByPrimaryKey(questionId);
		List<QuestionnaireAnswer> answers = qam.selectByQuestionId(new String[] { questionId });
		System.out.println(answers.size());
		map.put("answerssize", "22222222");
		map.put("question", question);
		map.put("answers", answers);
		return map;

	}

	public boolean insertQuestionAndAnswers(String mainId, Map<String, String> filePathMap,
			Map<String, String[]> answertext) {
		boolean bflag = true; // ����ɹ���ʧ�ܱ��
		String questionId = UUID.randomUUID().toString(); // ��������Id
		QuestionnaireQuestion qqEntity = new QuestionnaireQuestion(); // ��������ʵ����
		qqEntity.setQuestionCreat(new Date());
		qqEntity.setMainId(mainId); // ��������Id
		qqEntity.setQuestionDestype("file");
		qqEntity.setQuestionId(questionId); // ��������Id
		qqEntity.setQuestionTitle(answertext.get("questionTitle")[0]); // ��ȡ�������
		qqEntity.setQuestionType(answertext.get("questionType")[0]); // ��ȡ������
		if (qqm.insert(qqEntity) != 1) {
			return false; // ����ʧ�ܷ���false
		}
		answertext.remove("mainId"); // �Ӳ���Map�������mainId�ֶ�
		answertext.remove("questionTitle"); // �Ӳ���Map�������questionTitle�ֶ�
		answertext.remove("questionType"); // �Ӳ���Map�������questionType�ֶ�
//��������������ֶΣ���ôʣ�¶����Ǵ��ֶΣ���Mapת��ΪEntrySet
		Set<Entry<String, String[]>> answerEntry = answertext.entrySet();
//������
		for (Entry<String, String[]> tempTest : answerEntry) {
			String text = tempTest.getValue()[0]; // ��ȡ�𰸲���
			if (text != null && !"".equals(text.trim())) {
				QuestionnaireAnswer qaEntity = new QuestionnaireAnswer(); // ������ʵ����
				String filePath = filePathMap.get(tempTest.getKey()); // ��ȡ�ļ�·��
				if (filePath != null && !"".equals(filePath.trim())) { // �ж�·���Ƿ����
					qaEntity.setAnswerText(filePath); // ����������·��
					qaEntity.setAnswerType("y"); // y������ڸ���
//���ô������������û���ʾ���ڸ���
					qaEntity.setAnswerDestype(tempTest.getValue()[0] + "(������ظ���)");
				} else {
					qaEntity.setAnswerText("#"); // û�и�����ô���ݳ�ʼ��һ����#���ű��
					qaEntity.setAnswerType("n"); // n�������ڸ���
					qaEntity.setAnswerDestype(tempTest.getValue()[0]); // ���ô�����
				}
				qaEntity.setAnswerCreat(new Date());
				qaEntity.setAnswerId(UUID.randomUUID().toString()); // ���ô�Id
				qaEntity.setAnswerValue(0); // ���ô�ѡ����
				qaEntity.setQuestionId(questionId); // ��������Id
				qam.insert(qaEntity); // ��������
			}
		}
		return bflag; // ���ر��
	}

	public Map<String, Object> selectQuestionnaire(String mainId, boolean isStatistic) {
		Map<String, Object> questionnaire = new HashMap<String, Object>(); // ����ģ��
		QuestionnaireMain qm = qmm.selectByPrimaryKey(mainId); // ����mainId��ȡ��������
		// ����mainId��ȡ�������
		List<QuestionnaireQuestion> questions = qqm.selectByMainId(mainId);
		// ����һ��������������ڱ����id��Ϊ�˲�ѯ��
		String[] questionIds = new String[questions.size()];
		// �����������ݣ����questionIds����
		int i = 0;
		for (QuestionnaireQuestion entity : questions) {
			questionIds[i] = entity.getQuestionId();
			i++;
		}
		// ����һ��List����ȡ��
		List<QuestionnaireAnswer> answers = new ArrayList<>();
		if (questions.size() > 0) {
			answers = qam.selectByQuestionId(questionIds);
		}
		// ���������Ϊ��ʵ�ְٷֱȣ����Ի�ȡһ�����ѡ����
		Map<String, Integer> counts = new HashMap<>();
		for (QuestionnaireQuestion entity : questions) {
			String questionId = entity.getQuestionId();
			Integer count = 0;
			for (QuestionnaireAnswer answer : answers) {
				if (questionId.equals(answer.getQuestionId())) {
					count += answer.getAnswerValue();
				}
			}
			counts.put(entity.getQuestionId(), count);
		}
		// �����ͳ�ƽ������ô���ǻ�ȡquestionnaire_msg����
		if (isStatistic) {
			List<QuestionnaireMsg> msgs = msg.selectByMainId(mainId);
			questionnaire.put("msgs", msgs);
		}
		questionnaire.put("main", qm); // ģ�͵��д���������Ϣ
		questionnaire.put("question", questions); // ģ�͵��д���������Ϣ
		questionnaire.put("answer", answers); // ģ�͵��д������Ϣ
		questionnaire.put("counts", counts); // ģ�͵��д��������´�ѡ��������Ϣ

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

	public boolean updateCopyQuestion(String mainId, String username) {
		QuestionnaireMain quest = qmm.selectByPrimaryKey(mainId); // ����Id��ȡ��������
		if (quest != null) {
			String newMainId = UUID.randomUUID().toString(); // �½�һ������Id
			quest.setMainId(newMainId); // ��������Id
			quest.setMainIsuse("n"); // ���÷���״̬
			quest.setMainCreat(new Date()); // ���ô���ʱ��
			quest.setMainTitle(quest.getMainTitle() + "(����)"); // ���ÿ�������
			quest.setMainCreatuser(username); // ���ô�����
			qmm.insertSelective(quest); // ����������
			// ����mainId��ȡ��������
			List<QuestionnaireQuestion> oldQuestions = qqm.selectByMainId(mainId);
			if (oldQuestions != null) {
				// �����������ݣ���ѭ�������µ�����
				for (QuestionnaireQuestion oldQuestion : oldQuestions) {
					// ��ȡ��������Id
					String oldQuestionId = oldQuestion.getQuestionId();
					// �½�һ������Id
					String newQueationId = UUID.randomUUID().toString();
					// ��ԭ���ݵ�Id���ó�������Id
					oldQuestion.setQuestionId(newQueationId);
					// ��Դ���ݵ�����Id�޸ĳ��������Id
					oldQuestion.setMainId(newMainId);
					// �����������ʹ�õ��ǻ�ȡ����ģ��
					qqm.insert(oldQuestion);
					// ����Id��ȡ��
					List<QuestionnaireAnswer> oldAnswers = qam.selectByQuestionId(new String[] { oldQuestionId });
					// �жϴ��Ƿ�Ϊ��
					if (oldAnswers != null) {
						// �������ݣ���ѭ�������µĴ�
						for (QuestionnaireAnswer oldAnswer : oldAnswers) {
							// ����һ���µĴ�Id
							String newAnswerId = UUID.randomUUID().toString();
							// �����µ�Id
							oldAnswer.setAnswerId(newAnswerId);
							// �����µ�����Id
							oldAnswer.setQuestionId(newQueationId);
							// ��ʼ��ѡ�����
							oldAnswer.setAnswerValue(0);
							// ��������
							qam.insert(oldAnswer);
						}
					}
				}
			}
		} else {
			return false;
		}
		return true;
	}

}
