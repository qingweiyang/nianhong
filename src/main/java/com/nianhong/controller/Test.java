package com.nianhong.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
@RequestMapping("/")
public class Test {

	@RequestMapping(value = "upload1.do")
	public void upload2(String name, HttpServletRequest request,HttpServletResponse response) {
		System.out.println("inlineCheckbox1="+request.getParameter("inlineCheckbox1"));
		System.out.println("inlineCheckbox1="+request.getParameter("others"));
		String realpath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(realpath);
		//判断用户提交图片的文件夹是否存在
		File uploadFolder  = new File(realpath+"upload");
		if(!uploadFolder.exists()) {
			uploadFolder.mkdir();
		}
		//创建一个通用的多部分解析器.   
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		 //判断 request 是否有文件上传,即多部分请求...  
		if(multipartResolver.isMultipart(request))
		{
			 //判断 request 是否有文件上传,即多部分请求...  
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)(request);
			
			java.util.Iterator<String> iter = multiRequest.getFileNames();
			System.out.println("iter="+iter.hasNext());
			while(iter.hasNext()){
				MultipartFile file = multiRequest.getFile(iter.next());
				String fileName = "upload/" +file.getOriginalFilename();
				String path =realpath +fileName;
				File localFile = new File(path);
				System.out.println("path="+path);
				try {
					file.transferTo(localFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}		
		
//		return "/success";ß
	}
	
	
	
}
