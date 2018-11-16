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
		Map<String,Object> questionnaire  = new HashMap<String, Object>();  //返回模型
		QuestionnaireMain qm = qmm.selectByPrimaryKey(mainId);	//根据mainId获取主表数据
		//根据mainId获取问题参数
		List<QuestionnaireQuestion> questions = qqm.selectByMainId(mainId);
		//定义一个数组该数组用于保存答案id，为了查询答案
		String[] questionIds = new String[questions.size()];
		//迭代问题数据，填充questionIds数组
		int i = 0;
		for (QuestionnaireQuestion entity: questions){
			questionIds[i] = entity.getQuestionId();
			i++;
		}
		//定义一个List，获取答案
		List<QuestionnaireAnswer> answers = new ArrayList<>();
		if (questions.size()>0){
			answers = qam.selectByQuestionId(questionIds);
		}
		//下面代码是为了实现百分比，所以获取一组答案总选择数
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
		//如果是统计结果，那么我们获取questionnaire_msg数据
		if (isStatistic){
			List<QuestionnaireMsg> msgs=msg.selectByMainId(mainId);
			questionnaire.put("msgs",msgs);
		}
		questionnaire.put("main", qm);				//模型当中存入主表信息
		questionnaire.put("question", questions);	//模型当中存入问题信息
		questionnaire.put("answer", answers);		//模型当中存入答案信息
		questionnaire.put("counts", counts);		//模型当中存入问题下答案选择总数信息
		
		return questionnaire;
	}
	
	public boolean deleteMainItem(String mainId) {

		return qmm.deleteByPrimaryKey(mainId) == 1 ? true : false;

	}

	public int insertMain(QuestionnaireMain record) {
		int result = qmm.insertSelective(record); // 调用Dao层insertSelective方法
		return result; // 如果result等于1那么证明插入成功
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
		QuestionnaireMain quest = qmm.selectByPrimaryKey(mainId);	//根据Id获取主表数据
		if (quest!=null){
			String newMainId = UUID.randomUUID().toString();		//新建一个主表Id
			quest.setMainId(newMainId);								//设置主表Id
			quest.setMainIsuse("n");								//设置发布状态
			quest.setMainCreat(new Date());							//设置创建时间
			quest.setMainTitle(quest.getMainTitle()+"(副本)");		//设置拷贝标题
			quest.setMainCreatuser(username);						//设置创建人
			qmm.insertSelective(quest);								//插入新数据
			//根据mainId获取问题数据
			List<QuestionnaireQuestion> oldQuestions = qqm.selectByMainId(mainId);
			if (oldQuestions!=null){
				//迭代问题数据，并循环插入新的问题
				for (QuestionnaireQuestion oldQuestion : oldQuestions){
					//获取问题数据Id
					String oldQuestionId = oldQuestion.getQuestionId();
					//新建一个问题Id
					String newQueationId =  UUID.randomUUID().toString();
					//把原数据的Id设置成新数据Id
					oldQuestion.setQuestionId(newQueationId);
					//把源数据的主表Id修改成新主表的Id
					oldQuestion.setMainId(newMainId);
					//插入对象，这里使用的是获取对象模型
					qqm.insert(oldQuestion);
					//根据Id获取答案
					List<QuestionnaireAnswer> oldAnswers = 
							qam.selectByQuestionId(new String[]{oldQuestionId});
					//判断答案是否为空
					if (oldAnswers != null){
						//迭代数据，并循环插入新的答案
						for (QuestionnaireAnswer oldAnswer : oldAnswers){
							//创建一个新的答案Id
							String newAnswerId = UUID.randomUUID().toString();
							//设置新的Id
							oldAnswer.setAnswerId(newAnswerId);
							//设置新的问题Id
							oldAnswer.setQuestionId(newQueationId);
							//初始化选择答案数
							oldAnswer.setAnswerValue(0);
							//插入数据
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
