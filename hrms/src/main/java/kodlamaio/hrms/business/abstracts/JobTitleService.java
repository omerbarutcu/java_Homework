package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.entites.concretes.JobTitle;

public interface JobTitleService {

	void save(JobTitle jobTitle);
	List<JobTitle> getAll();
	JobTitle getById(int id);
}
