package com.ebox.WebTest;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	private static List<TestCase> testCaseList;
	static {
		testCaseList = new ArrayList<>();
	}
	
	@RequestMapping("/index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public ModelAndView add(@RequestParam("selector") String selector,@RequestParam("selectorValue")String value,@RequestParam("action")String action) {
		TestCase testCase = new TestCase(testCaseList.size()+1, selector, value, action, false);
		testCaseList.add(testCase);
		return new ModelAndView("case-table","testCaseList",testCaseList);
	}
	
	@RequestMapping(value="/addWithText",method=RequestMethod.POST)
	public ModelAndView add(@RequestParam("selector") String selector,@RequestParam("selectorValue")String value,@RequestParam("action")String action,@RequestParam("text")String text) {
		TestCase testCase = new TestCase(testCaseList.size()+1, selector, value, action, true, text);
		testCaseList.add(testCase);
		return new ModelAndView("case-table","testCaseList",testCaseList);
	}
	
	@RequestMapping("/show")
	public ModelAndView show() {
		return new ModelAndView("case-table","testCaseList",testCaseList);
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("id")Integer id) {
		for(TestCase t : testCaseList)
			if(Objects.equals(id, t.getId()))
				return new ModelAndView("By-option","testcase",t);
		return null;
	}
}
