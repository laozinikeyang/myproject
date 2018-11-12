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
	 * 保存问卷主页面
	 * @param mainTitle
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/question",method=RequestMethod.POST)
	public ModelAndView newQuestion (
			HttpServletRequest request,String mainTitle,String mainEndtime) 
																throws ParseException{
			//返回ModelAndView并将视图指向question方法重新获取数据
			ModelAndView mav = new ModelAndView("redirect:question");
			QuestionnaireMain main = new QuestionnaireMain();			//创建实体类
			main.setMainId(UUID.randomUUID().toString());				//创建实体类
			main.setMainIsuse("n");										//设置是否使用，是否发布
			main.setMainTitle(mainTitle);								//设置问卷标题
			main.setMainCreat(new Date());								//设置创建时间
			HttpSession session = request.getSession();					//获取session
			//在session中获取用户信息，获取当前用户真实姓名
			SysLogin loginEntity = (SysLogin)session.getAttribute("loginEntity");
			main.setMainCreatuser(loginEntity.getWxname());
			//设置截止时间
			if (mainEndtime!=null&&!"".equals(mainEndtime.trim())){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				main.setMainEndtime(sdf.parse(mainEndtime));
			}else{
				main.setMainEndtime(new Date());
			}
			//调用service层insertMain方法，将数据保存至数据库中
			questionService.insertMain(main);
			
		return mav;
	}
	
	
}
