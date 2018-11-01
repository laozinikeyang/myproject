package controller;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.QuestionnaireAnswer;
import service.TestService;

@Transactional
@Controller
public class TestController {
	@Autowired
	private TestService service;

	public TestService getService() {
		return service;
	}

	public void setService(TestService service) {
		this.service = service;
	}
	@RequestMapping(value="test.spring")
	public String test(Model model) {
		QuestionnaireAnswer questionnaireAnswer =service.selectByPrimaryKey("03a2173c-7a3e-498f-be34-359ae4a6ce4c");
		System.out.println(questionnaireAnswer.getAnswerId());
		return "index";
	}
}
