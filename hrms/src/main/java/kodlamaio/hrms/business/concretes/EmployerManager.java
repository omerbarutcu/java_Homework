package kodlamaio.hrms.business.concretes;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.adapters.abstracts.EmailService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.ResultChecker;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entites.concretes.Employer;
import kodlamaio.hrms.entites.concretes.VerificationCode;

@Service
public class EmployerManager implements EmployerService {

	@Autowired
	private EmployerDao employerDao;
	@Autowired
	private BusinessRuleService businessRuleService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private VerificationCodeService verificationCodeService;

	@Override
	public DataResult<List<Employer>> getAll() {

		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(), "Employer listelendi");
	}

	@Override
	public ResponseEntity<Result> add(Employer employer) {

		Result result = ResultChecker.check(Arrays.asList(businessRuleService.checkIfEmailExists(employer.getEmail()),
				businessRuleService.checkIfEmailContainsWebSiteDomain(employer.getEmail(), employer.getWebAddress()),
				businessRuleService.checkIfPasswordsMatch(employer.getPassword(), employer.getPasswordCheck())));
		if (result.isSuccess()) {
			employerDao.save(employer);
			VerificationCode verificationCode = addVerificationCode(employer);
			sendMail(employer.getEmail(),
					"Lütfen kodu kullanarak emailinizi onaylayın :"
							+ "http://localhost:8080/api/verification/confirm/" + employer.getUuid() + "/"
							+ verificationCode.getVerificationCode());
			return ResponseEntity.ok(new SuccessResult("Employer kaydı başarılı"));
		}
		return ResponseEntity.badRequest().body(new ErrorResult(result.getMessage()));

	}

	@Override
	public boolean existsById(int id) {
		return employerDao.existsById(id);
	}

	private VerificationCode addVerificationCode(Employer employer) {
		return verificationCodeService.add(employer);
	}

	private void sendMail(String email, String message) {
		emailService.sendMail(email, message);
	}
}
