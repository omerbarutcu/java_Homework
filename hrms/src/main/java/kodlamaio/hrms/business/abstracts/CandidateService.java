package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entites.concretes.Candidate;

public interface CandidateService {

	ResponseEntity<Result> add(Candidate candidate);

	DataResult<List<Candidate>> getAll();

}
