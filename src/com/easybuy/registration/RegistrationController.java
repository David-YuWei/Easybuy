package com.easybuy.registration;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.user.UserService;

@Controller("RegistrationController")
@RequestMapping("/registration")
public class RegistrationController {

	@Resource(name = "user:userService")
	private UserService userService;
	
	public RegistrationController(){
		
	}
	
	@RequestMapping(value = "/checkusername", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView check(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			String username= request.getParameter("username");
			
			String result = userService.checkUsername(username);
			if(result != null){
				model.put("msg", "UserName is not available");
			}
			else{
				model.put("msg", "<font style=\"color:green;\">UserName is available</font>");
			}
			
			model.put("status", "success");
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	} 
	
}
	
