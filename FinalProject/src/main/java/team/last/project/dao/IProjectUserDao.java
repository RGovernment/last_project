package team.last.project.dao;

import java.util.List;
import java.util.Map;

import team.last.project.dto.ProjectUserDto;

public interface IProjectUserDao {

	public List<?> listDao();

	public ProjectUserDto viewDao(String id);

	public int writeDao(Map<String, ?> map);

	public int articleCount();

}
