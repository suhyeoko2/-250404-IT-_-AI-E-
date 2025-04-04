package controls;

import java.util.Map;

import dao.ProjectDao;

public class ProjectListController implements Controller {
	ProjectDao projectDao;
	
	public ProjectListController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("projects", projectDao.selectList());
		return "/project/ProjectList.jsp";
	}
}
