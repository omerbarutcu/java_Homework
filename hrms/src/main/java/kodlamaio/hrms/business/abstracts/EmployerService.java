package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.entites.concretes.Employer;

public interface EmployerService {

	void save (Employer employer);
	List<Employer> getAll();
	Employer getById(int id);
}
