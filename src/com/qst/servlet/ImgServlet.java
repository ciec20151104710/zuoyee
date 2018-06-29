package com.qst.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/ImgServlet")
public class ImgServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 BufferedImage bi = new BufferedImage(80,35,BufferedImage.TYPE_INT_RGB);
	        Graphics g = bi.getGraphics();
	        //先画一个显示验证码的底板，为红色，大小为80 35
	        g.setColor(Color.red);
	        g.fillRect(0, 0, 80, 35);
	        /**
	         * 设置验证码
	         */
	        char[] arry = "abcdefg1234567890".toCharArray();
	        Random r = new Random();
	        StringBuffer sb = new StringBuffer();
	        for(int i=0; i<4; i++) {
	            int index = r.nextInt(arry.length);
	            Font f = new Font(Font.MONOSPACED,Font.BOLD,30); 
	            g.setFont(f);
	            g.setColor((new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255))));
	            /**
	             * drawString(String star, dint x, dint y) 
	            使用此图形上下文的当前字体和颜色绘制由指定 string 给定的文本。
	             */
	            g.drawString(arry[index]+"", i*20, 30);
	            sb.append(arry[index]);
	        }
	//将验证码的内容放在session中
	        request.getSession().setAttribute("piccode", sb.toString());   
	//图片的输出流，返回到客户端
	        ImageIO.write(bi, "JPG", response.getOutputStream());
	    }   
	
	
	
	
	}


