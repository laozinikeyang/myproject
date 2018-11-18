package controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import entity.*;
import service.QuestionnaireService;
import standard.Util;
@Transactional
@Controller
public class TestController {
	@Autowired
	private Util  ut;
	@Autowired
	SimpleDateFormat sdf;
	@Autowired QuestionnaireService questionService;
//	@RequestMapping(value="/question1.spring")
//	public String test() {return "editQuestionnaire";}
	
	
	@RequestMapping(value="question/statistics/{mainId}.spring",method=RequestMethod.GET)
	public ModelAndView statistics (@PathVariable String mainId){
		ModelAndView mav = new ModelAndView("statistics");
		
		Map<String, Object> questionnaire = 
				questionService.selectQuestionnaire(mainId,true);	//获取完整问卷
		//把完整问卷放置于ModelAndView当中，这等同于放置在request域当中，方便JSP页面获取
		mav.addObject("questionnaire", questionnaire);				
		
		return mav;
	}
	
	
	@RequestMapping(value="question/delWt/{questionId}.spring",method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String,Boolean> delWt (@PathVariable String questionId){
		
		Map<String,Boolean> resultMap = new HashMap<>();
		resultMap.put("success", questionService.delectQuestionAndAnswer(questionId));
		
		return resultMap;
	}
	
	@RequestMapping(value="question/editQuestionTestAndFile.spring",method=RequestMethod.POST)
	public ModelAndView editQuestionTestAndFile(MultipartHttpServletRequest request
									,String mainId,String questionId) throws IOException{
		//转发重定向前面使用了redirect前缀，为了让URL路径始终保持在编辑页面
		ModelAndView mav = new ModelAndView("redirect:/question/edit/"+mainId+".spring");
		//获取所有文字参数以Map的形式呈现
		Map<String, String[]> parms = request.getParameterMap();
		//获取文件参数以Map形式呈现
		Map<String, MultipartFile> fileMap = request.getFileMap();
		//保存文件路径的Map
		Map<String,String> resultFileMap = new HashMap<>();
		//获取EntrySet，Entry是Map的键值对。
		Set<Entry<String, MultipartFile>> parmEntrySet = fileMap.entrySet();
		//使用for循环迭代文件并上传文件
		for (Entry<String, MultipartFile> tempEntry : parmEntrySet){
			MultipartFile mf = tempEntry.getValue();
			if (mf.getSize()>0){
				//设置文件名称，为避免重复使用UUID
				String newFileName = UUID.randomUUID().toString();
				//获取文件上传路径（绝对路径）
				String ph = request.getSession().getServletContext()
												.getRealPath("/upload");
				//获取文件名的原始名称，即从客户端上传过来的文件名称
				String oldFileName = mf.getOriginalFilename();
				//截取扩展名
				String ext = oldFileName.substring(oldFileName.lastIndexOf("."));
				//拼接文件完成路径
				StringBuffer sb = new StringBuffer(ph).append("/file/");
				sb.append(newFileName).append(ext);
				File file = new File(sb.toString());
				//调用调用org.apache.commons.io.FileUtils的copyInputStreamToFile写入文件
				FileUtils.copyInputStreamToFile(mf.getInputStream(), file);
				//获取文件相对路径
				String filepath = new StringBuffer("upload/file/").append(newFileName)
																.append(ext).toString();
				//使用Map保存路径，键为前台传递过来的guid，值为上传文件的相对路径
				resultFileMap.put(tempEntry.getKey(), filepath);
			}
		}
		//调用Service层updateQuestionAndAnswers方法更新问题与答案
		questionService.updateQuestionAndAnswers(mainId, resultFileMap, parms);
		
		return mav;
	}
	
	
	@RequestMapping(value="question/editMainTitle.spring",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> editMainTitle (String mainId,String mainTitle,String mainEndtime){
		//通过@ResponseBody注解可以将返回的Map转换为json格式
		Map<String,Boolean> resultMap = new HashMap<>();
		//调用service层当中的updateMainTitle方法进行插入或修改操作。
		resultMap.put("success", questionService.updateMainTitle(mainId, mainTitle,mainEndtime));

		return resultMap;
	}
	
	
	@RequestMapping(value="question/editQuestionnaire/{questionId}.spring",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> editQuestionnaire(@PathVariable String questionId){
	System.out.println("controller");
		return questionService.selectQuestionByIdToAnswer(questionId);
	}
	
	
	
	@RequestMapping(value="question/addQuestionTestAndFile.spring",method=RequestMethod.POST)
	public ModelAndView addQuestionTestAndFile(MultipartHttpServletRequest request
													,String mainId) throws IOException{
		//转发重定向前面使用了redirect前缀，为了让URL路径始终保持在编辑页面
		ModelAndView mav = new ModelAndView("redirect:/question/edit/"+mainId+".spring");
		//获取所有文字参数以Map的形式呈现
		Map<String, String[]> parms = request.getParameterMap();
		//获取文件参数以Map形式呈现
		Map<String, MultipartFile> fileMap = request.getFileMap();
		//保存文件路径的Map
		Map<String,String> resultFileMap = new HashMap<>();
		//获取EntrySet，Entry是Map的键值对。
		Set<Entry<String, MultipartFile>> parmEntrySet = fileMap.entrySet();
		//使用for循环迭代文件并上传文件
		for (Entry<String, MultipartFile> tempEntry : parmEntrySet){
			MultipartFile mf = tempEntry.getValue();
			if (mf.getSize()>0){
				//设置文件名称，为避免重复使用UUID
				String newFileName = UUID.randomUUID().toString();
				//获取文件上传路径（绝对路径）
				String ph = request.getSession().getServletContext()
												.getRealPath("/upload");
				//获取文件名的原始名称，即从客户端上传过来的文件名称
				String oldFileName = mf.getOriginalFilename();
				//截取扩展名
				String ext = oldFileName.substring(oldFileName.lastIndexOf("."));
				//拼接文件完成路径
				StringBuffer sb = new StringBuffer(ph).append("/file/");
				sb.append(newFileName).append(ext);
				File file = new File(sb.toString());
				//调用调用org.apache.commons.io.FileUtils的copyInputStreamToFile写入文件
				FileUtils.copyInputStreamToFile(mf.getInputStream(), file);
				//获取文件相对路径
				String filepath = new StringBuffer("upload/file/").append(newFileName)
						.append(ext).toString();
				//使用Map保存路径，键为前台传递过来的guid，值为上传文件的相对路径
				resultFileMap.put(tempEntry.getKey(), filepath);
			}
		}
		//调用Service层insertQuestionAndAnswers方法保存问题与答案
		questionService.insertQuestionAndAnswers(mainId,resultFileMap,parms);

		return mav;
	}
	
	
	
	
	@RequestMapping(value="/question/edit/{mainId}.spring",method=RequestMethod.GET)
	public ModelAndView editQuestion (@PathVariable String mainId){
		ModelAndView mav = new ModelAndView("editQuestionnaire");
		
		Map<String, Object> questionnaire = questionService.selectQuestionnaire(mainId,false);
		mav.addObject("questionnaire", questionnaire);
		
		return mav;
	}
	
	
	
	@RequestMapping(value="question/copy/{mainId}.spring",method=RequestMethod.GET)
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	@RequiresAuthentication
	public Map<String,Boolean> copyQuestion (HttpServletRequest request,@PathVariable String mainId){
		Map<String,Boolean> resultMap = new HashMap<>();
		HttpSession session = request.getSession();
//		Sys_login loginEntity = (Sys_login)session.getAttribute("loginEntity");
		try{
//			resultMap.put("success", questionService.updateCopyQuestion(mainId,loginEntity.getWxname()));
			resultMap.put("success", questionService.updateCopyQuestion(mainId, "test"));
		}catch(Exception e){
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	
	@RequestMapping(value="question/pause/{mainId}.spring",method=RequestMethod.PUT)
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public Map<String,Boolean> pause (@PathVariable String mainId){
		Map<String,Boolean> resultMap = new HashMap<>();
		
		QuestionnaireMain record = new QuestionnaireMain();
		record.setMainId(mainId);
		record.setMainIsuse("n");
		resultMap.put("success", questionService.updateMainAction(record));
		
		return resultMap;
	}
	
	
	@RequestMapping(value="question/action/{mainId}.spring",method=RequestMethod.PUT)
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public Map<String,Boolean> actionStart (@PathVariable String mainId){
		Map<String,Boolean> resultMap = new HashMap<>();
		
		QuestionnaireMain record = new QuestionnaireMain();
		record.setMainId(mainId);
		record.setMainIsuse("y");
		resultMap.put("success", questionService.updateMainAction(record));
		
		return resultMap;
	}
	
	@RequestMapping(value="/question/delCheck.spring",method=RequestMethod.POST)
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public Map<String,Boolean> delCheck(HttpServletRequest request,String[] id){
		
		Map<String,Boolean> resultMap = new HashMap<>();

		if (questionService.deleteCheck(id)){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value="/question/del/{mainId}.spring",method=RequestMethod.DELETE)
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public Map<String,Boolean> deleteQuestion (@PathVariable String mainId){
		Map<String,Boolean> resultMap = new HashMap<>();
		
		if(questionService.deleteMainItem(mainId)){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	@RequestMapping(value="/question.spring",method=RequestMethod.GET)
	public ModelAndView view (@RequestParam(name="page",defaultValue="0") Integer page
			,@RequestParam(name="row",defaultValue="10")Integer row,String mainTitle
			,String mainStartTime,String mainOverTime) throws ParseException{
		ModelAndView mav = new ModelAndView("question");
		Map<String,Object> parm = new HashMap<>();
		if (page > 0){
			parm.put("page", (page-1)*row);
		}else{
			parm.put("page", page);
		}
		parm.put("row", row);
		if (mainTitle!=null&&!"".equals(mainTitle.trim())){
			parm.put("mainTitle", mainTitle);
		}
		if (mainStartTime!=null&&Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(mainTitle).matches()){
			parm.put("mainStartTime", sdf.parse(mainStartTime));
		}
		if (mainOverTime!=null&&Pattern.compile("[0-9]{4}-[0-9]{2}-[0-9]{2}").matcher(mainTitle).matches()){
			parm.put("mainOverTime", sdf.parse(mainOverTime));
		}
		List<QuestionnaireMain> mainList = questionService.findMainPage(parm);
		Long count = questionService.findCount(parm);
		Long temp= count%row;
		Long countPage = 0L;
		if (temp==0){
			countPage = count/row;
		}else{
			countPage = count/row+1;
		}
		String currentPage = null;
		if (page >0){
			currentPage = ut.page(page, Integer.valueOf(countPage+""));
		}else{
			currentPage = ut.page(page+1, Integer.valueOf(countPage+""));
		}
		mav.addObject("mainList", mainList);
		mav.addObject("currentPage", currentPage);
		
		
		return mav;
	}
	/**
	 * 保存问卷主页面
	 * @param mainTitle
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/question.spring",method=RequestMethod.POST)
	public ModelAndView newQuestion (
			HttpServletRequest request,String mainTitle,String mainEndtime) 
																throws ParseException{
			//返回ModelAndView并将视图指向question方法重新获取数据
			ModelAndView mav = new ModelAndView("redirect:question.spring");
			QuestionnaireMain main = new QuestionnaireMain();			//创建实体类
			main.setMainId(UUID.randomUUID().toString());				//创建实体类
			main.setMainIsuse("n");										//设置是否使用，是否发布
			main.setMainTitle(mainTitle);								//设置问卷标题
			main.setMainCreat(new Date());								//设置创建时间
			HttpSession session = request.getSession();					//获取session
			//在session中获取用户信息，获取当前用户真实姓名
//			SysLogin loginEntity = (SysLogin)session.getAttribute("loginEntity");
//			main.setMainCreatuser(loginEntity.getWxname());
			main.setMainCreatuser("test");
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
