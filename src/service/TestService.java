package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.QuestionnaireAnswerMapper;
import entity.QuestionnaireAnswer;

@Service
public class TestService {
	@Autowired
	private QuestionnaireAnswerMapper answerMapper;

	public QuestionnaireAnswerMapper getAnswerMapper() {
		return answerMapper;
	}

	public void setAnswerMapper(QuestionnaireAnswerMapper answerMapper) {
		this.answerMapper = answerMapper;
	}
	
	public QuestionnaireAnswer selectByPrimaryKey(String answerId) {
		return answerMapper.selectByPrimaryKey(answerId);
		
	}
}
