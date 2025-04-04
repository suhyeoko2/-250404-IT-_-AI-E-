package controls;

import java.util.Map;

import bind.DataBinding;
import dao.ProjectDao;

public class ProjectDeleteController implements Controller, DataBinding {
	ProjectDao projectDao;
	
	public ProjectDeleteController setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
		return this;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[]{"pno", Integer.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Integer pno  = (Integer)model.get("pno");
		projectDao.delete(pno);
		return "redirect:list.do";
	}
}
