package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dto.Member;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		String query = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(sc.getInitParameter("url"), 
											   sc.getInitParameter("username"),
											   sc.getInitParameter("password"));
			System.out.println("conn>>>>>" + conn);
			//3. 쿼리수행해서 처리
			query = "select * from members";
			st = conn.createStatement();
			rs  = st.executeQuery(query);
			
			//**** view에 전달할 Data 준비해서 request에 저장하고 위임하기
			ArrayList<Member> members = new ArrayList<Member>();
			
			while(rs.next()) {
				Member tmp = new Member();
				tmp.setMno(rs.getInt("mno"));
				tmp.setEmail(rs.getString("email"));
				tmp.setMname(rs.getString("mname"));
				tmp.setPwd(rs.getString("pwd"));
				tmp.setCre_date(rs.getDate("cre_date"));
				tmp.setMod_date(rs.getDate("mod_date"));
				
				members.add(tmp);
			}
			
			//view 가 사용할 수있도록 request 영역에 저장
			request.setAttribute("members", members);
			
			// view위임하기
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			
			//4. 화면 출력 - 응답
//			PrintWriter out = response.getWriter();
//			out.print("<html><head><title>회원목록</title></head>");
//			out.print("<body><h1>회원목록</h1><a href='add'>신규등록</a><hr>");
//			
//			while(rs.next()) {
//				out.println(rs.getInt("mno") + "," + "<a href='update?mno=" + rs.getInt("mno") +"'>" +rs.getString("mname") + "</a><br>");
//			}
//			
//			out.print("</body></html>");
			
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
			
		}finally {
			//5. DB 해제
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
