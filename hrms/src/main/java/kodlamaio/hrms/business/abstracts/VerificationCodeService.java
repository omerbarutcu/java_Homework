package kodlamaio.hrms.business.abstracts;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import kodlamaio.hrms.core.entites.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entites.concretes.VerificationCode;

public interface VerificationCodeService {

	VerificationCode add(User user);

	ResponseEntity<DataResult<VerificationCode>> findByUserUuid(UUID uuid);

	ResponseEntity<Result> confirm(VerificationCode code, String verificatinCode);
}
