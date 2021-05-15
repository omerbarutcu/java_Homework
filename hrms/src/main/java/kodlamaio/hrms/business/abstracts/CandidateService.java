package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.entites.concretes.Candidate;

public interface CandidateService {

	void save(Candidate candidate);
	List<Candidate> getAll();
	Candidate getById(int id);
	
}
