package com.java.reply.command;

import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.java.command.Command;
import com.java.reply.model.ReplyDao;
import com.java.reply.model.ReplyDto;

public class ReplyWriteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String writeReply = request.getParameter("writeReply");
		String user_ip = request.getRemoteAddr();
		
		logger.info(logMsg + user_ip + writeReply);
		
		ReplyDto replyDto = new ReplyDto();
		replyDto.setLine_reply(writeReply);
		replyDto.setUser_ip(user_ip);
		
		int check = ReplyDao.getInstance().insert(replyDto);
		
		if(check>0) {
			//제대로 댓글이 데이터베이스에 저장되고 바로 화면에 반영될 수 있게
			int bunho = ReplyDao.getInstance().getBunho();
			
			//jsp와 java는 jstl을 이용해 request로 데이터를 주고받을 수 있다.
			//java와 javascript는 데이터를 주고받으려면 text로 주고받아야 한다.
			//JSON - 행 하나에 열이 여러개
			//JSONarray - 행 여러개,열 여러개
			
			HashMap<String,Object> map = new HashMap<String, Object>();
			map.put("bunho", bunho);
			map.put("line_reply", writeReply);
			map.put("user_ip", user_ip);
			
			JSONObject obj = new JSONObject(map); // 파라미터 > 문자 형식으로
			//JSON이 넘어갈 때 application/json 폴더 동작 x
			response.setContentType("application/text;charset=utf-8");
			PrintWriter pw = response.getWriter();
			//print메소드의 경우 소켓스트림을 통해 java에서 js로 이동
			pw.print(obj);
			
		}
		
		return null;
	}

}
