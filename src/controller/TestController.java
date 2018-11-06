package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView test(ModelAndView mav,String abc) {
		System.out.println("abc:"+abc);
		QuestionnaireAnswer questionnaireAnswer =service.selectByPrimaryKey("03a2173c-7a3e-498f-be34-359ae4a6ce4c");
		System.out.println(questionnaireAnswer.getAnswerId());
		mav.addObject("bb", questionnaireAnswer);
		mav.setViewName("error");;
		return mav;
	}
	@RequestMapping(value="test1.spring")
	public ModelAndView test1(ModelAndView mav,String abc) {
		
		mav.setViewName("NewFile");;
		return mav;
	}
	
}
