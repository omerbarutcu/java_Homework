package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entites.concretes.JobTitle;

public interface JobTitleService {

	ResponseEntity<Result>  add(JobTitle jobTitle);

	DataResult<List<JobTitle>> getAll();
}
