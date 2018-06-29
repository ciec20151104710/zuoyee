package com.qst.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.DBUtil;


@WebServlet("/serlet")
public class serlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");	
	
		Connection conn = DBUtil.getConnection();
		String sql="select * from username where username=? and pwd=?";
		//List<user> list = new ArrayList<user>();
		PreparedStatement pstm =null;
		ResultSet rs = null ;
		user user = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, pwd);
			rs = pstm.executeQuery();
			while(rs.next()) {
				user=new user();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("username"));
				user.setPwd(rs.getString("pwd"));
				//list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtil.CloseDB(conn,pstm,rs);
		}
		 String piccode=(String) request.getSession().getAttribute("piccode");
	        String checkCode=request.getParameter("checkCode");  //取值
	      
	        if(checkCode.equals(piccode))
	        {
	        	
	    		if(user!=null) {
	    			HttpSession session1 = request.getSession();
	    			session1.setAttribute("user", user);
	    			response.sendRedirect("success.jsp");
	    		}else {
	    			response.sendRedirect("loging.jsp");
	    		}
	        }
	        else
	        {
	        	response.sendRedirect("loging.jsp");
	        	
	        }
	        
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
