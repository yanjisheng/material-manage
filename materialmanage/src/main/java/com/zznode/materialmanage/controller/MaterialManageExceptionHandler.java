package com.zznode.materialmanage.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zznode.materialmanage.exception.MaterialManageException;
import com.zznode.materialmanage.util.DateTimeFormatter;

/**
 * 针对业务异常进行统一处理
 * @author yanjisheng
 *
 */
@ControllerAdvice
public class MaterialManageExceptionHandler{

	private static Logger log = LoggerFactory.getLogger(MaterialManageExceptionHandler.class);
	
	private static int errorCount = 0;
	
	@Autowired
	ObjectMapper json;
	
	@ExceptionHandler(MaterialManageException.class)
	public void handler(HttpServletRequest request, HttpServletResponse response, MaterialManageException e) throws JsonProcessingException{
		log.error(e.getMessage(), e);
		String accept = request.getHeader("Accept");
		String responseBody;
		if(accept!=null && accept.indexOf("text/")>=0){
			StringBuffer sb = new StringBuffer();
			sb.append("<!DOCTYPE HTML>\n");
			sb.append("<html>\n");
			sb.append("<head><title>错误</title></head>\n");
			sb.append("<body>\n");
			sb.append("<h1>您发送了一个错误的请求</h1>\n");
			if(errorCount >= 10){
				sb.append("<h2>我们已经报警，请立即停止攻击并到公安局自首！</h2>");
			}
			sb.append("<p>时间："+DateTimeFormatter.toDateTime(new Date())+"</p>\n");
			sb.append("<p>错误信息："+e.getMessage()+"</p>\n");
			if(e.getCause() != null){
				sb.append("<p>rootError: "+ e.getCause().getClass().getName()+"</p>\n");
				sb.append("<p>rootMessage: "+ e.getCause().getMessage()+"</p>\n");
			}
			sb.append("<p>路径："+request.getRequestURI()+"</p>\n");
			sb.append("<p><a href=\"http://localhost:8888/\">回到首页</a></p>\n");
			sb.append("</body></html>");
			responseBody = sb.toString();
			errorCount++;
			doResponse(responseBody, "text/html;charset=UTF-8", response);
		}
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("timestamp", new Date());
		map.put("status", 700);
		map.put("error", "Material manage exception");
		map.put("message", e.getMessage());
		if(e.getCause() != null){
			map.put("rootError", e.getCause().getClass().getName());
			map.put("rootMessage", e.getCause().getMessage());
		}
		map.put("path", request.getRequestURI());
		map.put("params", request.getParameterMap());
		responseBody = json.writeValueAsString(map);
		doResponse(responseBody, "application/json;charset=UTF-8", response);
	}
	
	private void doResponse(String responseBody, String responseType, HttpServletResponse response){
		try {
			response.setContentType(responseType);
			PrintWriter out = response.getWriter();
			out.write(responseBody);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void resetErrorCount(){
		errorCount = 0;
	}
	
}