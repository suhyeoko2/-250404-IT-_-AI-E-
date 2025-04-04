package listeners;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import controls.ProjectAddController;
import controls.ProjectDeleteController;
import controls.ProjectListController;
import controls.ProjectUpdateController;
import dao.ProjectDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce)  { 
    	try {
    		ServletContext sc = sce.getServletContext();
    		
    		InitialContext ic = new InitialContext();
    		DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/members");
    		
    		ProjectDao projectDao = new ProjectDao();
    		projectDao.setDataSource(ds);
    		
    		sc.setAttribute("/project/list.do", new ProjectListController().setProjectDao(projectDao));
    		sc.setAttribute("/project/add.do", new ProjectAddController().setProjectDao(projectDao));
    		sc.setAttribute("/project/update.do", new ProjectUpdateController().setProjectDao(projectDao));
    		sc.setAttribute("/project/delete.do", new ProjectDeleteController().setProjectDao(projectDao));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }
	
}
