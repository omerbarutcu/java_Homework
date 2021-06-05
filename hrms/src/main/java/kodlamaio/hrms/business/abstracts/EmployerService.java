package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entites.concretes.Employer;

public interface EmployerService {

	ResponseEntity<Result> add(Employer employer);

	DataResult<List<Employer>> getAll();

	boolean existsById(int id);
}
