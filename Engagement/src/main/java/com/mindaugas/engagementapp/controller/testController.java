package com.mindaugas.engagementapp.controller;






import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class testController {

	
	
	@RequestMapping(value="/test/ajax_test")
	public String testpage(){
		return "test";
	}
	@RequestMapping(value="/test/get_time")
	@ResponseBody
	public String getServerTime(){
		java.util.Date date = new java.util.Date();
		return date.toString();
	}


}
