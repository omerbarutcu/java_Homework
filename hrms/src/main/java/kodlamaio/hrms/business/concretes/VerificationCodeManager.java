package kodlamaio.hrms.business.concretes;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.entites.User;
import kodlamaio.hrms.core.utilities.helpers.CodeGenerator;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.VerificationCodeDao;
import kodlamaio.hrms.entites.concretes.Employer;
import kodlamaio.hrms.entites.concretes.VerificationCode;

@Service
public class VerificationCodeManager implements VerificationCodeService {

	private final VerificationCodeDao verificationCodeDao;
	private final EmployerDao employerDao;
	private final EmployerVerifyService employerVerifyService;

	@Autowired
	public VerificationCodeManager(VerificationCodeDao verificationCodeDao, EmployerDao employerDao,
			EmployerVerifyService employerVerifyService) {
		
		this.verificationCodeDao = verificationCodeDao;
		this.employerDao = employerDao;
		this.employerVerifyService = employerVerifyService;
	}

	@Override
	public VerificationCode add(User user) {
		VerificationCode verificatinCode = new VerificationCode();
		verificatinCode.setVerificationCode(CodeGenerator.generateCode());
		verificatinCode.setVerified(false);
		verificatinCode.setUser(user);
		return verificationCodeDao.save(verificatinCode);
	}

	@Override
	public ResponseEntity<DataResult<VerificationCode>> findByUserUuid(UUID uuid) {
		VerificationCode code = verificationCodeDao.findByUserUuid(uuid).orElse(null);

		if (code == null) {
			return ResponseEntity.badRequest().body(new ErrorDataResult<>("Doğrulama kodu bulunamadı !"));
		} else {
			return ResponseEntity.ok(new SuccessDataResult<>(code, "Kod bulundu"));
		}
	}

	@Override
	public ResponseEntity<Result> confirm(VerificationCode code, String verificatinCode) {
		if (code.getVerificationCode().equals(verificatinCode)) {
			code.setVerified(true);
			verificationCodeDao.save(code);
			Employer employer = employerDao.findByUuid(code.getUser().getUuid()).orElse(null);
			employerVerifyService.add(employer);
			return ResponseEntity
					.ok(new SuccessResult("Kullanıcı oluşturuldu" + "Lütfen sistem personeli ile irtibata geçiniz !"));
		} else {
			return ResponseEntity.badRequest().body(new ErrorResult("Doğrulama kodunuz eşleşmedi"));
		}
	}

}
