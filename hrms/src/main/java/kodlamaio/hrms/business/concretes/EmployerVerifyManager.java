package kodlamaio.hrms.business.concretes;

import java.util.Arrays;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.ResultChecker;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerVerifyDao;
import kodlamaio.hrms.entites.concretes.Employee;
import kodlamaio.hrms.entites.concretes.Employer;
import kodlamaio.hrms.entites.concretes.EmployerVerify;

@Service
public class EmployerVerifyManager implements EmployerVerifyService {

	@Autowired
	private EmployerVerifyDao employerVerifyDao;

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private BusinessRuleService businessRuleService;

	@Override
	public DataResult<EmployerVerify> add(Employer employer) {
		EmployerVerify employerVerify = new EmployerVerify();
		employerVerify.setEmployer(employer);
		employerVerify.setConfirmed(false);
		return new SuccessDataResult<>(employerVerifyDao.save(employerVerify));
	}

	@Override
	public ResponseEntity<Result> verifyEmployer(UUID employerUuid, UUID employeeUuid) {

		EmployerVerify employerVerify = employerVerifyDao.findByEmployerUuid(employerUuid).orElse(null);
		Employee employee = employeeDao.findByUuid(employeeUuid).orElse(null);

		Result result = ResultChecker
				.check(Arrays.asList(businessRuleService.checkIfEmployerVerifyExists(employerVerify),
						businessRuleService.checkIfEmployeeExists(employee),
						businessRuleService.checkIfUuidValid(employerUuid, "Employer"),
						businessRuleService.checkIfUuidValid(employeeUuid, "System personnel"),
						checkIfEmployerAlreadyVerified(employerUuid)));

		if (result.isSuccess()) {
			employerVerify.setEmployee(employee);
			employerVerify.setConfirmed(true);
			employerVerifyDao.save(employerVerify);
			return ResponseEntity.ok(new SuccessResult("Employer onayı başarılı"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResult(result.getMessage()));
		}
	}

	private Result checkIfEmployerAlreadyVerified(UUID uuid) {
		if (employerVerifyDao.existsByEmployerUuidAndConfirmedTrue(uuid)) {
			return new ErrorDataResult<>("Employer zaten onaylı");
		} else {
			return new SuccessResult();
		}
	}
}
