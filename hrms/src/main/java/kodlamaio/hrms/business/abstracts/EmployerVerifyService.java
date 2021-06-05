package kodlamaio.hrms.business.abstracts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entites.concretes.Employer;
import kodlamaio.hrms.entites.concretes.EmployerVerify;

public interface EmployerVerifyService {

	DataResult<EmployerVerify> add(Employer employer);

	ResponseEntity<Result> verifyEmployer(UUID employerUuid, UUID employeeUuid);
}
