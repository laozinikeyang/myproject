package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import entity.*;
import service.QuestionnaireService;
@Transactional
@Controller
public class TestController {
	
	
	@Autowired QuestionnaireService questionService;
	/**
	 * �����ʾ���ҳ��
	 * @param mainTitle
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/question",method=RequestMethod.POST)
	public ModelAndView newQuestion (
			HttpServletRequest request,String mainTitle,String mainEndtime) 
																throws ParseException{
			//����ModelAndView������ͼָ��question�������»�ȡ����
			ModelAndView mav = new ModelAndView("redirect:question");
			QuestionnaireMain main = new QuestionnaireMain();			//����ʵ����
			main.setMainId(UUID.randomUUID().toString());				//����ʵ����
			main.setMainIsuse("n");										//�����Ƿ�ʹ�ã��Ƿ񷢲�
			main.setMainTitle(mainTitle);								//�����ʾ����
			main.setMainCreat(new Date());								//���ô���ʱ��
			HttpSession session = request.getSession();					//��ȡsession
			//��session�л�ȡ�û���Ϣ����ȡ��ǰ�û���ʵ����
			SysLogin loginEntity = (SysLogin)session.getAttribute("loginEntity");
			main.setMainCreatuser(loginEntity.getWxname());
			//���ý�ֹʱ��
			if (mainEndtime!=null&&!"".equals(mainEndtime.trim())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				main.setMainEndtime(sdf.parse(mainEndtime));
			}else{
				main.setMainEndtime(new Date());
			}
			//����service��insertMain�����������ݱ��������ݿ���
			questionService.insertMain(main);
			
		return mav;
	}
	
	
}
