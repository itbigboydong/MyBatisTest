package com.ca.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ca.domain.User;
import com.ca.server.DaoFactory;
import com.ca.server.UserService;
import com.ca.util.MyBatisUtil;

public class LoginFilter implements Filter{
	
	  private  FilterConfig config;

	public void destroy() {
		
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,FilterChain chain) 
			throws IOException, ServletException {
		
		    HttpServletRequest req = (HttpServletRequest) arg0;
		    HttpServletResponse res = (HttpServletResponse) arg1;
		    
		    PrintWriter pw = res.getWriter();
		    
		      SqlSessionFactory sqlSessionFactoty = MyBatisUtil.getSqlsessionfactory();
		      SqlSession sqlSession = sqlSessionFactoty.openSession();
		      
		      String result = null;
		      try {
				result= handle(req,res,sqlSession, chain);
				sqlSession.commit();
			} catch (Exception e) {
				sqlSession.rollback();
				
			}finally{
				if(sqlSession != null){
					sqlSession.close();
				}
			}
		    
		   pw.write(result);
		   pw.close();
		
	}

	private String handle(HttpServletRequest req,HttpServletResponse res,SqlSession sqlSession,
			         FilterChain chain) throws IOException, ServletException {
		
		//判断是否包含非过滤的地址，非过滤地址就直接放行
		   String noLoginPaths = config.getInitParameter("noLoginPaths");
		   if(noLoginPaths!=null){
			   String[] paths = noLoginPaths.split(";");
			   for(int i=0;i<paths.length;i++){
				   if(paths[i] != null ||"".equals(paths[i]))continue;
				   if(req.getRequestURI().indexOf(paths[i]) !=-1){
					  chain.doFilter(req, res);
					 return "";
					  
				   }
			   }
			   
		   }
		
		String userId =req.getParameter("userId");
		String verify=req.getParameter("verify");
		
		DaoFactory daoFactoty = DaoFactory.getDaoFactory();
		UserService userService = daoFactoty.getUserService(sqlSession);
		User user =userService.findVerify(userId);
		String DBVerify = user.getVerify();
		if(DBVerify.equals(verify)){
			chain.doFilter(req, res);
			JSONObject json = new JSONObject();
			json.put("code", "0");
			return json.toString();
			
		}else{
			JSONObject json = new JSONObject();
			json.put("code", "1");
			json.put("mess", "请先登录");
			return json.toString();
		}
	   	 
	}

	public void init(FilterConfig arg0) throws ServletException {
	    config=arg0;
		
	}

}
