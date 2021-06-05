package kodlamaio.hrms.business.concretes;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.business.rules.BusinessRuleService;
import kodlamaio.hrms.core.adapters.abstracts.EmailService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.ResultChecker;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entites.concretes.Candidate;
import kodlamaio.hrms.entites.concretes.VerificationCode;

@Service
public class CandidateManager implements CandidateService {

	@Autowired
	private CandidateDao candidateDao;

	@Autowired
	private BusinessRuleService businesRuleService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private VerificationCodeService verificationCodeService;

	@Override
	public ResponseEntity<Result> add(Candidate candidate) {

		if (candidate.getBirthDate() == null) {
			return ResponseEntity.badRequest().body(new ErrorResult("Doğum yılı boş olamaz"));
		}

		Result result = ResultChecker.check(Arrays.asList(businesRuleService.checkIfEmailExists(candidate.getEmail()),
				checkIfNationalIdentityExists(candidate.getIdentityNumber()),
				businesRuleService.checkIfUserInformationCorrect(candidate.getIdentityNumber(),
						candidate.getFirstName(), candidate.getLastName(),
						candidate.getBirthDate().toLocalDate().getYear()),
				businesRuleService.checkIfPasswordsMatch(candidate.getPassword(), candidate.getPasswordCheck()))

		);

		if (result.isSuccess()) {
			candidateDao.save(candidate);

			VerificationCode verificationCode = addVerificationCode(candidate);
			sendMail(candidate.getEmail(),
					"Lütfen kodu kullanarak emailinizi onaylayın :"
							+ "http://localhost:8080/api/verification/verify-candidate/" + candidate.getUuid()
							+ "/" + verificationCode.getVerificationCode());
			return ResponseEntity.ok(new SuccessResult("Candidate kaydı başarılı"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResult(result.getMessage()));
		}
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll(), "Candidate listelendi");
	}

	private VerificationCode addVerificationCode(Candidate candidate) {
		return verificationCodeService.add(candidate);
	}

	private void sendMail(String email, String message) {
		emailService.sendMail(email, message);
	}

	private Result checkIfNationalIdentityExists(String nationalIdentity) {
		if (candidateDao.existsByIdentityNumber(nationalIdentity)) {
			return new ErrorResult("An user exists with this national identity.");
		} else {
			return new SuccessResult();
		}
	}

}
