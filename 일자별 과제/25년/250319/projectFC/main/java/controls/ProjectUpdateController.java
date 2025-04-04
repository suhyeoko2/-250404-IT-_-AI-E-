package controls;

import java.util.Map;

import bind.DataBinding;
import dao.ProjectDao;
import dto.Project;

public class ProjectUpdateController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	public ProjectUpdateController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] {"pno", Integer.class, "project", dto.Project.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project = (Project)model.get("project");
		
		if(project.getPname() == null) {
			Integer pno = (Integer)model.get("pno");
			Project projectOne = projectDao.selectOne(pno);
			model.put("project", projectOne);
			return "/project/ProjectUpdateForm.jsp";
		} else {
			projectDao.update(project);
			return "redirect:list.do";
		}
	}

}
