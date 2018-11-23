package controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import javax.annotation.Resource;
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

import dao.SysLoginMapper;
import dao.SysRoleMapper;
import entity.*;
import service.QuestionnaireService;
import servicedao.Sys_permissionService;
import servicedao.Sys_permissionTreeService;
import servicedao.Sys_role_permissionService;
import standard.Util;
@Transactional
@Controller
public class TestController {
	@Autowired
	private Util  ut;
	@Autowired
	SimpleDateFormat sdf;
	@Autowired 
	QuestionnaireService questionService;
	@Autowired
	SysRoleMapper roleService;
	@Autowired
	SysLoginMapper userloginService;
	
	@Resource
	Sys_permissionService perService;
	@Resource
	Sys_permissionTreeService perTreeService;
	@Resource
	Sys_role_permissionService sysRolPerService;
	
	@RequestMapping("role/addView.spring")
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public ModelAndView getAdd(HttpServletRequest request,HttpServletResponse response,Sys_roleTree role) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("roleadd");
		return mav;
	}
	
	
	
	@RequestMapping("role/editFieldView.spring")
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public ModelAndView getEditField(HttpServletRequest request,HttpServletResponse response,SysRole role) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("entity", role);
		
		return mav;
	}
	
	
	@RequestMapping("role/addRolePer.spring")
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public Map<String,String> addRolePer(HttpServletRequest request,HttpServletResponse response,String roleName,Integer roleId,String perparm) {
		Map<String,String> mav = new HashMap<String, String>();
		String[] splitParm = perparm.split(",");
		int i = 4;
		if (i>0){
			sysRolPerService.updateRolPer(roleName, roleId, splitParm);
			mav.put("success", "OK");
			mav.put("msg", "保存成功");
		}else{
			mav.put("success", "NO");
			mav.put("msg", "保存失败");
		}
		return mav;
	}
	
	
	
	@RequestMapping("role/getTreeCheck.spring")
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public List<Sys_permissionTree> getTreeCheck(HttpServletRequest request,HttpServletResponse response,String roleName) {
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		List<Sys_permissionTree> listPer = null;
		
		listPer = perTreeService.selectAll();
		
		return perTreeService.checked(listPer, roleName);
	}
	
	
	
	@RequestMapping("role/editView.spring")
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public ModelAndView getEdit(HttpServletRequest request,HttpServletResponse response,SysRole role) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("role_per");
		mav.addObject("entity", role);
		/*List<Sys_role_permission> checked = sysRolPerService.selectByRoleName(role.getRoleName());
		if (checked!=null){
			mav.addObject("checked", checked);
		}*/
		return mav;
	}
	
	
	@RequestMapping("role/getTree.spring")
	@ResponseBody
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public List<Sys_roleTree> getTree(HttpServletRequest request,HttpServletResponse response) {
		System.out.println("controller:role/getTree.spring");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
	
		List<Sys_roleTree> listRole = roleService.selectTreeAll();
		
		return listRole;
	}
	
	@RequestMapping("role/mainView.spring")
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public ModelAndView getMainView (){
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("role");
		
		return mav;
	}
	
	@RequestMapping(value="login/del/{loginId}.spring",method=RequestMethod.DELETE)
	@ResponseBody
	public Map<String,Boolean> delUser (@PathVariable Integer loginId){
		Map<String,Boolean> resultMap = new HashMap<>();
		
		if (userloginService.deleteByPrimaryKey(loginId)==1){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		}
		
		return resultMap;
	}
	
	
	
	@RequestMapping(value="login/updateRole.spring",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> updateRole (Integer roleId,Integer loginId,String username){
		System.out.println("controller:updateRole");
		Map<String,Boolean> resultMap = new HashMap<>();
		
		Map<String,Object> parm = new HashMap<>();
		parm.put("roleId", roleId);
		parm.put("loginId", loginId);
		parm.put("username", username);
		if (userloginService.updateSys_login_roleSelective(parm)==1){
			resultMap.put("success", true);
		}else{
			resultMap.put("success", false);
		};
		
		return resultMap;
	}
	
	
	@RequestMapping(value="login/user.spring",method=RequestMethod.GET)
//	@RequiresRoles(value = { "admin" },logical=Logical.OR)
	public ModelAndView view (@RequestParam(name="page",defaultValue="0") Integer page,@RequestParam(name="row",defaultValue="10")Integer row){
		ModelAndView mav = new ModelAndView("user");
		List<Sys_roleTree> roles = roleService.selectTreeAll();
		Map<String,Object> parm = new HashMap<>();
		if (page > 0){
			parm.put("page", (page-1)*row);
		}else{
			parm.put("page", page);
		}
		parm.put("row", row);
		List<Map<String,Object>> mainList = questionService.selectLoginRole(parm);
		Long count = userloginService.selectCount().get("count");
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
		mav.addObject("roles", roles);
		return mav;
	}
	
	@RequestMapping(value="question/actionSub.spring",method=RequestMethod.POST)
	@ResponseBody
//	@RequiresAuthentication
	public Map<String,Boolean> actionSub (HttpServletRequest request){
		Map<String,Boolean> resultMap = new HashMap<>();
		
		HttpSession session = request.getSession();
		String username = (String)(session.getAttribute("UserName")!=null?session.getAttribute("UserName"):"");
		String mainId = request.getParameter("mainId");
		//Sys_login sysLoginEntity = (Sys_login)session.getAttribute("loginEntity");
		
//		if(questionService.selectMainUser(username,mainId)){
		if(true){
			String message = request.getParameter("message");
			List<String> parms = new ArrayList<>();
			Map<String, String[]> parm = request.getParameterMap();
			Set<Entry<String, String[]>> entrySet = parm.entrySet();
			for (Entry<String, String[]> entry : entrySet){
				String[] answerIds = entry.getValue();
				parms.addAll(Arrays.asList(answerIds));
			}
//			resultMap.put("success", questionService.updateValueIn(parms,username,mainId,message,sysLoginEntity.getWxname()));
			resultMap.put("success", questionService.updateValueIn(parms,username,mainId,message,"test"));
		}else{
			resultMap.put("success", false);
		}

		return resultMap;
	}
	
	
	@RequestMapping(value="question/go/{mainId}.spring",method=RequestMethod.GET)
	public ModelAndView goQuestionPage (HttpServletRequest request,@PathVariable String mainId){
		
		ModelAndView mav = new ModelAndView("lowquestion");
		
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("UserName");
		Boolean bflag = true; //questionService.selectMainUser(username, mainId);����Ȩ�޸��Ӻ���
		if (bflag){  // �����жϵ�������û��ش���������򲻽����ҳ��
			mav.setViewName("lowquestion");
			Map<String, Object> questionnaire = questionService.selectQuestionnaire(mainId,false);
			mav.addObject("questionnaire", questionnaire);
		}else{
			mav.setViewName("lowpage/lowError");
		}
		return mav;
	}
	
	@RequestMapping(value = "index.spring", method = RequestMethod.GET)
	//@RequiresAuthentication	//��֤ͨ���û����Ե�¼
	public ModelAndView home (HttpServletRequest request,@RequestParam(name="page",defaultValue="0") Integer page,@RequestParam(name="row",defaultValue="10")Integer row){
		ModelAndView mav = new ModelAndView("index");
		
		Map<String,Object> parm = new HashMap<>();
		if (page > 0){
			parm.put("page", (page-1)*row);
		}else{
			parm.put("page", page);
		}
		parm.put("row", row);
		parm.put("mainEndtime",new Date());
		parm.put("mainIsuse","y");
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
	
	@RequestMapping(value="question/statistics/{mainId}.spring",method=RequestMethod.GET)
	public ModelAndView statistics (@PathVariable String mainId){
		ModelAndView mav = new ModelAndView("statistics");
		
		Map<String, Object> questionnaire = 
				questionService.selectQuestionnaire(mainId,true);	//��ȡ�����ʾ�
		//�������ʾ������ModelAndView���У����ͬ�ڷ�����request���У�����JSPҳ���ȡ
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
		//ת���ض���ǰ��ʹ����redirectǰ׺��Ϊ����URL·��ʼ�ձ����ڱ༭ҳ��
		ModelAndView mav = new ModelAndView("redirect:/question/edit/"+mainId+".spring");
		//��ȡ�������ֲ�����Map����ʽ����
		Map<String, String[]> parms = request.getParameterMap();
		//��ȡ�ļ�������Map��ʽ����
		Map<String, MultipartFile> fileMap = request.getFileMap();
		//�����ļ�·����Map
		Map<String,String> resultFileMap = new HashMap<>();
		//��ȡEntrySet��Entry��Map�ļ�ֵ�ԡ�
		Set<Entry<String, MultipartFile>> parmEntrySet = fileMap.entrySet();
		//ʹ��forѭ�������ļ����ϴ��ļ�
		for (Entry<String, MultipartFile> tempEntry : parmEntrySet){
			MultipartFile mf = tempEntry.getValue();
			if (mf.getSize()>0){
				//�����ļ����ƣ�Ϊ�����ظ�ʹ��UUID
				String newFileName = UUID.randomUUID().toString();
				//��ȡ�ļ��ϴ�·��������·����
				String ph = request.getSession().getServletContext()
												.getRealPath("/upload");
				//��ȡ�ļ�����ԭʼ���ƣ����ӿͻ����ϴ��������ļ�����
				String oldFileName = mf.getOriginalFilename();
				//��ȡ��չ��
				String ext = oldFileName.substring(oldFileName.lastIndexOf("."));
				//ƴ���ļ����·��
				StringBuffer sb = new StringBuffer(ph).append("/file/");
				sb.append(newFileName).append(ext);
				File file = new File(sb.toString());
				//���õ���org.apache.commons.io.FileUtils��copyInputStreamToFileд���ļ�
				FileUtils.copyInputStreamToFile(mf.getInputStream(), file);
				//��ȡ�ļ����·��
				String filepath = new StringBuffer("upload/file/").append(newFileName)
																.append(ext).toString();
				//ʹ��Map����·������Ϊǰ̨���ݹ�����guid��ֵΪ�ϴ��ļ������·��
				resultFileMap.put(tempEntry.getKey(), filepath);
			}
		}
		//����Service��updateQuestionAndAnswers���������������
		questionService.updateQuestionAndAnswers(mainId, resultFileMap, parms);
		
		return mav;
	}
	
	
	@RequestMapping(value="question/editMainTitle.spring",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Boolean> editMainTitle (String mainId,String mainTitle,String mainEndtime){
		//ͨ��@ResponseBodyע����Խ����ص�Mapת��Ϊjson��ʽ
		Map<String,Boolean> resultMap = new HashMap<>();
		//����service�㵱�е�updateMainTitle�������в�����޸Ĳ�����
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
		//ת���ض���ǰ��ʹ����redirectǰ׺��Ϊ����URL·��ʼ�ձ����ڱ༭ҳ��
		ModelAndView mav = new ModelAndView("redirect:/question/edit/"+mainId+".spring");
		//��ȡ�������ֲ�����Map����ʽ����
		Map<String, String[]> parms = request.getParameterMap();
		//��ȡ�ļ�������Map��ʽ����
		Map<String, MultipartFile> fileMap = request.getFileMap();
		//�����ļ�·����Map
		Map<String,String> resultFileMap = new HashMap<>();
		//��ȡEntrySet��Entry��Map�ļ�ֵ�ԡ�
		Set<Entry<String, MultipartFile>> parmEntrySet = fileMap.entrySet();
		//ʹ��forѭ�������ļ����ϴ��ļ�
		for (Entry<String, MultipartFile> tempEntry : parmEntrySet){
			MultipartFile mf = tempEntry.getValue();
			if (mf.getSize()>0){
				//�����ļ����ƣ�Ϊ�����ظ�ʹ��UUID
				String newFileName = UUID.randomUUID().toString();
				//��ȡ�ļ��ϴ�·��������·����
				String ph = request.getSession().getServletContext()
												.getRealPath("/upload");
				//��ȡ�ļ�����ԭʼ���ƣ����ӿͻ����ϴ��������ļ�����
				String oldFileName = mf.getOriginalFilename();
				//��ȡ��չ��
				String ext = oldFileName.substring(oldFileName.lastIndexOf("."));
				//ƴ���ļ����·��
				StringBuffer sb = new StringBuffer(ph).append("/file/");
				sb.append(newFileName).append(ext);
				File file = new File(sb.toString());
				//���õ���org.apache.commons.io.FileUtils��copyInputStreamToFileд���ļ�
				FileUtils.copyInputStreamToFile(mf.getInputStream(), file);
				//��ȡ�ļ����·��
				String filepath = new StringBuffer("upload/file/").append(newFileName)
						.append(ext).toString();
				//ʹ��Map����·������Ϊǰ̨���ݹ�����guid��ֵΪ�ϴ��ļ������·��
				resultFileMap.put(tempEntry.getKey(), filepath);
			}
		}
		//����Service��insertQuestionAndAnswers���������������
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
	 * �����ʾ���ҳ��
	 * @param mainTitle
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/question.spring",method=RequestMethod.POST)
	public ModelAndView newQuestion (
			HttpServletRequest request,String mainTitle,String mainEndtime) 
																throws ParseException{
			//����ModelAndView������ͼָ��question�������»�ȡ����
			ModelAndView mav = new ModelAndView("redirect:question.spring");
			QuestionnaireMain main = new QuestionnaireMain();			//����ʵ����
			main.setMainId(UUID.randomUUID().toString());				//����ʵ����
			main.setMainIsuse("n");										//�����Ƿ�ʹ�ã��Ƿ񷢲�
			main.setMainTitle(mainTitle);								//�����ʾ����
			main.setMainCreat(new Date());								//���ô���ʱ��
			HttpSession session = request.getSession();					//��ȡsession
			//��session�л�ȡ�û���Ϣ����ȡ��ǰ�û���ʵ����
//			SysLogin loginEntity = (SysLogin)session.getAttribute("loginEntity");
//			main.setMainCreatuser(loginEntity.getWxname());
			main.setMainCreatuser("test");
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
