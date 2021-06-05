package kodlamaio.hrms.business.rules;

import java.util.UUID;

import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entites.concretes.Employee;
import kodlamaio.hrms.entites.concretes.EmployerVerify;

public interface BusinessRuleService {

	Result checkIfPasswordsMatch(String password, String passwordCheck);

	Result checkIfEmailExists(String email);

	Result checkIfEmailContainsWebSiteDomain(String email, String website);

	Result checkIfEmployerVerifyExists(EmployerVerify employerVerify);

	Result checkIfEmployeeExists(Employee employee);

	Result checkIfUserInformationCorrect(String nationalIdentity, String name, String surname, int year);
	
	Result checkIfUuidValid(UUID uuid, String person);
}
