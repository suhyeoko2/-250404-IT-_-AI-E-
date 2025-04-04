package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/member/MemberAddForm.jsp");
		rd.forward(request, response);
		
		//신규등록 화면을 전송
//		PrintWriter out = response.getWriter();
//		out.print("<html><head><title>신규회원 등록</title></head>");
//		out.print("<body><h1>신규회원 등록</h1><hr>");
//		out.print("<form action='add' method='post'>");
//		out.print("이름 : <input type='text' name='mname'><br>");
//		out.print("이메일 : <input type='text' name='email'><br>");
//		out.print("비번 : <input type='password' name='pwd'><br>");
//		out.print("<input type='submit' value='등록'><input type='reset' value='취소'>");
//		out.print("</form></body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//화면에서 전송된 정보를 db에 저장
		//1. 전송된 정보 저장
		String mname = request.getParameter("mname");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
	
		//2. DB 연결해서 쿼리만들어 저장후 DB 해제
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), 
											   sc.getInitParameter("username"),
											   sc.getInitParameter("password"));
			query = "insert into members values (seq_members.nextval, ?,?,?,sysdate,sysdate)";
			pst = conn.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, pwd);
			pst.setString(3, mname);
			
			pst.executeUpdate();  //DB에서는 저장
			
			response.sendRedirect("list");
			
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}finally {
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
