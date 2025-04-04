package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 수정요청된 pk를 저장
		int mno = Integer.parseInt(request.getParameter("mno"));
		
		//DB 연결해서 쿼리만들어 정보요청해서 화면에 출력하고  Db 해제
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String query = null;
		
		try {
			ServletContext sc = this.getServletContext();
			//1. DB 로드
			Class.forName(sc.getInitParameter("driver"));
			//2. DB 연결
			conn = DriverManager.getConnection(sc.getInitParameter("url"), 
											   sc.getInitParameter("username"),
											   sc.getInitParameter("password"));
			query = "select * from members where mno = ?";
			pst = conn.prepareStatement(query);
			pst.setInt(1, mno);
			
			rs = pst.executeQuery();
			
			//rs에 있는 정보를 DTO에 저장하고 request에 보관한뒤 view 위임하기
			if(rs.next()) {
				Member member = new Member();
				member.setMno(rs.getInt("mno"));
				member.setMname(rs.getString("mname"));
				member.setEmail(rs.getString("email"));
				member.setPwd(rs.getString("pwd"));
				member.setCre_date(rs.getDate("cre_date"));
				member.setMod_date(rs.getDate("mod_date"));
				
				request.setAttribute("member", member);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberUpdate.jsp");
			rd.include(request, response);
			
			
//			if(rs.next()) {
//				//화면출력
//				
//				PrintWriter out = response.getWriter();
//				out.print("<html><head><title>회원 조회 및 수정</title></head>");
//				out.print("<body><h1>회원 조회 및 수정</h1><hr>");
//				out.print("<form action='update' method='post'>");
//				out.print("번호 : <input type='text' name='mno' value='"+ rs.getInt("mno") +"' readonly><br>");
//				out.print("이름 : <input type='text' name='mname' value='"+ rs.getString("mname")+"'><br>");
//				out.print("이메일 : <input type='text' name='email' value='"+ rs.getString("email") +"'><br>");
//				out.print("가입일 : " + rs.getDate("cre_date")+"<br>");
//				out.print("수정일 : " + rs.getDate("mod_date")+"<br>");
//				out.print("<input type='submit' value='수정'> <a href='delete?mno="+rs.getInt("mno")+"'>[삭제]</a><input type='reset' value='취소'>");
//				out.print("</form></body></html>");
//			}
			
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}finally {
			try {
				if(rs != null) rs.close();
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정된 전송정보를 저장
		int mno = Integer.parseInt(request.getParameter("mno"));
		String mname = request.getParameter("mname");
		String email = request.getParameter("email");
		
		System.out.println(mno + mname + email);
		
		Connection conn = null;
		PreparedStatement pst = null;
		String query = null;
		
		//DB 연결해서 쿼리만들어 수행하고 리스트화면 요청
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			
			conn = DriverManager.getConnection(sc.getInitParameter("url"), 
											   sc.getInitParameter("username"),
											   sc.getInitParameter("password"));
			
			query = "update members set mname=?, email=?, mod_date=sysdate where mno=?";
			
			pst = conn.prepareStatement(query);
			pst.setString(1, mname);
			pst.setString(2, email);
			pst.setInt(3, mno);
			
			pst.executeUpdate();
			
			response.sendRedirect("list");
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		} finally {
			try {
				if(pst != null) pst.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
