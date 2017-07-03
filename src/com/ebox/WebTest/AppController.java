package com.ebox.WebTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request) {
		if(request.getSession().getAttribute("testCaseList") == null)
			request.getSession().setAttribute("testCaseList", new ArrayList<TestCase>());
//		System.out.println(request);
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request,@RequestParam("selector") String selector,@RequestParam("selectorValue")String value,@RequestParam("action")String action) {
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>)request.getSession().getAttribute("testCaseList");
		TestCase testCase = null;
		if(testCaseList.size() == 0)
			testCase = new TestCase(testCaseList.size()+1, selector, value, action, false);
		else
			testCase = new TestCase(testCaseList.get(testCaseList.size()-1).getId()+1,selector,value,action,false);
		testCaseList.add(testCase);
		request.getSession().setAttribute("testCaseList", testCaseList);
		return new ModelAndView("case-table","testCaseList",request.getSession().getAttribute("testCaseList"));
	}
	
	@RequestMapping(value="/addWithText",method=RequestMethod.POST)
	public ModelAndView add(HttpServletRequest request,@RequestParam("selector") String selector,@RequestParam("selectorValue")String value,@RequestParam("action")String action,@RequestParam("text")String text) {
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>)request.getSession().getAttribute("testCaseList");
		TestCase testCase = null;
		if(testCaseList.size() == 0)
			testCase = new TestCase(testCaseList.size()+1, selector, value, action, true, text);
		else
			testCase = new TestCase(testCaseList.get(testCaseList.size()-1).getId()+1, selector, value, action, true, text);
		testCaseList.add(testCase);
		request.getSession().setAttribute("testCaseList", testCaseList);
		return new ModelAndView("case-table","testCaseList",request.getSession().getAttribute("testCaseList"));
	}
	
	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>)request.getSession().getAttribute("testCaseList");
		return new ModelAndView("case-table","testCaseList",testCaseList);
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(HttpServletRequest request,@RequestParam("id")Integer id) {
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>)request.getSession().getAttribute("testCaseList");
		for(TestCase testCase : testCaseList)
			if(testCase.getId().equals(id))
				return new ModelAndView("update","testCase",testCase);
		return null;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView update(HttpServletRequest request, @RequestParam("id") Integer id,
			@RequestParam("selector") String selector, @RequestParam("selectorValue") String value,
			@RequestParam("action") String action) {
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>) request.getSession().getAttribute("testCaseList");
		for (TestCase t : testCaseList) {
			if (t.getId().equals(id)) {
				t.setSelector(selector);
				t.setAction(action);
				t.setSelectorValue(value);
			}
		}
		request.getSession().setAttribute("testCaseList", testCaseList);
		return new ModelAndView("case-table", "testCaseList", request.getSession().getAttribute("testCaseList"));
	}
	
	@RequestMapping(value = "/updateWithText", method = RequestMethod.POST)
	public ModelAndView updateWithText(HttpServletRequest request, @RequestParam("id") Integer id,
			@RequestParam("selector") String selector, @RequestParam("selectorValue") String value,
			@RequestParam("action") String action, @RequestParam("text") String text) {
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>)request.getSession().getAttribute("testCaseList");
		for (TestCase t : testCaseList) {
			if (t.getId().equals(id)) {
				t.setSelector(selector);
				t.setAction(action);
				t.setSelectorValue(value);
				t.setText(text);
				t.setIsText(true);
			}
		}
		request.getSession().setAttribute("testCaseList", testCaseList);
		return new ModelAndView("case-table", "testCaseList", request.getSession().getAttribute("testCaseList"));
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(HttpServletRequest request, @RequestParam("id") Integer id) {
		int i;
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>) request.getSession().getAttribute("testCaseList");
		for (i = 0; i < testCaseList.size(); i++) {
			if (testCaseList.get(i).getId().equals(id))
				break;
		}
		try {
			testCaseList.remove(i);
			request.getSession().setAttribute("testCaseList", testCaseList);
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("case-table", "testCaseList", request.getSession().getAttribute("testCaseList"));
	}
	
	@RequestMapping("/finish")
	public String finish(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		List<TestCase> testCaseList = (List<TestCase>)request.getSession().getAttribute("testCaseList");
		WebTestEngine.setTestCaseList(testCaseList);
		try {
			new WebTestEngine().generateTest();
			request.getSession().setAttribute("fileName",WebTestEngine.getFileName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "finish";
	}
	
	@RequestMapping("/download")
	public void downloadWebTest(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String fileName = (String)request.getSession().getAttribute("fileName");
		String filePath = "G:\\Workspace JAVA\\Projects\\Testcase files\\"+fileName;
		File file = new File(filePath);
		OutputStream out = response.getOutputStream();
		response.setContentType("application/download");
		response.setHeader("Content-Disposition", "Attachment; Filename=\"WebTest.java\"");
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[4096];
		int length;
		while ((length = in.read(buffer)) > 0){
		    out.write(buffer, 0, length);
		}
		in.close();
		out.flush();
		request.getSession().invalidate();
	}
}
