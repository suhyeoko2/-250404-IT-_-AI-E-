package controls;

import java.util.Map;

import bind.DataBinding;
import dao.ProjectDao;
import dto.Project;

public class ProjectAddController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	public ProjectAddController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[]{"project", dto.Project.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project)model.get("project");
		if(project.getPname() == null) {
			return "/project/ProjectAddForm.jsp";
		} else {
			projectDao.insert(project);
			return "redirect:list.do";
		}
	}
}
