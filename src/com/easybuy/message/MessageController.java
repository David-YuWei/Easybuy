package com.easybuy.message;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.easybuy.common.Pager;
import com.easybuy.message.domain.Message;
import com.easybuy.product.domain.Product;
import com.easybuy.user.UserService;
import com.easybuy.user.domain.User;
import com.easybuy.user.domain.Buyer;
import com.easybuy.user.domain.Seller;

@Controller("messageController")
@RequestMapping("/message")
public class MessageController {
	
	@Resource(name = "message:messageService")
	private MessageService messageService;
	@Resource(name = "user:userService")
	private UserService userService;
	
	public MessageController(){
		
	}

	@RequestMapping(value = "", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toReceivedList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/message/received");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/receivedList", method = {RequestMethod.GET})
	public ModelAndView receivedList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{5}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Message> messages = messageService.getReceivedList(pager,user.getUser_name());
			model.put("status", "success");
			model.put("pager", pager);
			model.put("list", messages);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/sent", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView toSent(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/message/sent");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	@RequestMapping(value = "/sentList", method = {RequestMethod.GET})
	public ModelAndView sentList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "page", required = false, defaultValue = "#{1}") Integer page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "#{20}") Integer pageSize) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		try {
			User user = userService.getUser(request);
			Pager pager = new Pager(page, pageSize > 50 ? 50 : pageSize);
			List<Message> messages = messageService.getSentList(pager,user.getUser_name());
			model.put("status", "success");
			model.put("pager", pager);
			model.put("list", messages);
		} catch (Exception e) {
			model.put("status", "error");
			e.printStackTrace();
		} finally {
			mav.addObject("_model", model);
		}
		return mav;
	}
	
	@RequestMapping(value = "/message_view", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView message_view(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "message_id", required = true) int message_id) throws ServletException {
		ModelAndView mav = new ModelAndView();
		try {
				Message message = messageService.getById(message_id);
				mav.addObject("messageInfo", message);
				mav.setViewName("/message/message_view");
				return mav;
			}
		 catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}

	@RequestMapping(value = "/message_new", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tonew(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		try {
			mav.setViewName("/message/message_new");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	
	
	@RequestMapping(value = "/new", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView tonew1(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "touser", required = true) String touser ) {
		ModelAndView mav = new ModelAndView();
		try {
			Message message = new Message();
			message.setTouser(touser);
			mav.addObject("message",message);
			mav.addObject("disable","disable");
			mav.setViewName("/message/message_new");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/messageNew", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mav = new ModelAndView();
		Message message = new Message();
		try {
			String touser = request.getParameter("touser");
			String content = request.getParameter("content");
			
			message.setTouser(touser);
			message.setContent(content);
			if (StringUtils.isBlank(touser) || StringUtils.isBlank(content)) {
				mav.addObject("error", "To/Content should not be empty.");
				mav.addObject("message", message);
				mav.setViewName("/message/message_new");
				return mav;
			}
			if(content.length() > 1000){
				mav.addObject("error", "Content too long.");
				mav.addObject("message", message);
				mav.setViewName("/message/message_new");
				return mav;
			}
			
			User user = userService.getUser(request);
			
			message.setFromuser(user.getUser_name());
			message.setType("4");
			boolean result = messageService.insert(message);
			if(result){
				mav.setViewName("/message/sent");
			}
			else{
				mav.addObject("message", message);
				mav.addObject("error", "send failed.");
				mav.setViewName("/message/message_new");
			}
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("message", message);
			mav.addObject("error", "save failed.");
			mav.setViewName("/message/message_new");
		} finally {
		}
		return mav;
	}
	
	
	
//	
//	@RequestMapping(value = "/test", method = {RequestMethod.GET, RequestMethod.POST})
//	public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		ModelAndView mav = new ModelAndView();
//		try {
//				boolean testvar;
//				testvar=messageService.sendRegistrationNotif("yws");
//				testvar=messageService.sendOrderNotif("yws", 123);
//				testvar=messageService.sendOrderUpdateNotif("yws",1234);
//				testvar=messageService.sendReviewNotif("yws",323);
//				int i=15;
//				i=i+1;
//			}
//		 catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//		}
//		return mav;
//	}
	
	}


