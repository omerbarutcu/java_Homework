package kodlamaio.hrms.business.rules;

import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.adapters.abstracts.UserCheckService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.entites.concretes.Employee;
import kodlamaio.hrms.entites.concretes.EmployerVerify;

@Service
public class BusinessRuleManager implements BusinessRuleService {

	private final UserService userService;
	private final UserCheckService userCheckService;

	@Autowired
	public BusinessRuleManager(UserService userService, UserCheckService userCheckService) {

		this.userService = userService;
		this.userCheckService = userCheckService;
	}

	@Override
	public Result checkIfPasswordsMatch(String password, String passwordCheck) {
		if (!password.equals(passwordCheck)) {
			return new ErrorResult("Şifreler aynı olmalı !");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkIfEmailExists(String email) {
		
		if (userService.existsByEmail(email)) {
			return new ErrorResult("Bu email zaten kullanımda");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkIfEmailContainsWebSiteDomain(String email, String website) {
		if (!email.split("@")[1].contains(website)) {
			return new ErrorResult("Email websitesi alanı ile aynı olmalı !");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkIfEmployerVerifyExists(
			EmployerVerify employerVerify) {
		if (employerVerify==null) {
			return new ErrorResult("Employer kimliği eşleşmedi");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkIfEmployeeExists(Employee employee) {
		if (employee==null) {
			return new ErrorResult("Sistem personeli kullanıcı kimliği eşleşmedi");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkIfUserInformationCorrect(String nationalIdentity, String name, String surname, int year) {
		if (!userCheckService.validate(nationalIdentity, name, name, null)) {
			return new ErrorResult("Kullanıcı bilgileri yanlış !");
		}
		return new SuccessResult();
	}

	@Override
	public Result checkIfUuidValid(UUID uuid, String person) {
		String regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        Pattern pattern = Pattern.compile(regexp);
        if(!pattern.matcher(uuid.toString()).matches()){
            return new ErrorResult(person + " UUID onaylı değil.");
        } else {
            return new SuccessResult();
        }
	}

}
