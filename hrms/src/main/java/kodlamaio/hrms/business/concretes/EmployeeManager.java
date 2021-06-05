package kodlamaio.hrms.business.concretes;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployeeService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.ResultChecker;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployeeDao;
import kodlamaio.hrms.entites.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private BusinessRuleService businessRuleService;

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>(this.employeeDao.findAll(), "Sistem personelleri listelendi");
	}

	@Override
	public ResponseEntity<Result> add(Employee employee) {
		Result result = ResultChecker.check(Arrays.asList(businessRuleService.checkIfEmailExists(employee.getEmail()),
				businessRuleService.checkIfPasswordsMatch(employee.getPassword(), employee.getPasswordCheck())));

		if (result.isSuccess()) {
			employeeDao.save(employee);
			return ResponseEntity.ok(new SuccessResult("Sistem personeli eklendi"));
		}
		return ResponseEntity.badRequest().body(new ErrorResult(result.getMessage()));
	}

}
