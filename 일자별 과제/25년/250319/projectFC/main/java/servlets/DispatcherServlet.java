package servlets;

import java.io.IOException;
import java.util.HashMap;

import bind.DataBinding;
import bind.ServletRequestDataBinder;
import controls.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//어떤 요청인지 확인.
		String servletPath = request.getServletPath();
		System.out.println(servletPath);
		
		try {
			ServletContext sc = this.getServletContext();
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			
			Controller pageController = (Controller)sc.getAttribute(servletPath);
				
			if(pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			//pc를 실행한뒤 결과로 view의 정보를 받는다.
			String viewUrl = pageController.execute(model);
			System.out.println(viewUrl);
			
			//model에 있는 정보를 view에 전달하기 위해 request에 저장한다.
			for(String key: model.keySet()) {
				System.out.println(key);
				request.setAttribute(key, model.get(key));
			}
			
			//뷰를 위임한다.
			if(viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd= request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}
			System.out.println("==========");
		}catch(Exception e){
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		}
	} 
	
	private void prepareRequestData(HttpServletRequest request,
		      HashMap<String, Object> model, DataBinding dataBinding)
		      throws Exception {
	    Object[] dataBinders = dataBinding.getDataBinders();
	    String dataName = null;
	    Class<?> dataType = null;
	    Object dataObj = null;
	    
	    for (int i = 0; i < dataBinders.length; i+=2) {
	      dataName = (String)dataBinders[i];
	      dataType = (Class<?>) dataBinders[i+1];
	      dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
	      model.put(dataName, dataObj);
	    }
	}
	
}
