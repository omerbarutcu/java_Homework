package kodlamaio.hrms.core.adapters.concretes;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.adapters.abstracts.EmailService;
import kodlamaio.hrms.externalServices.email.FakeEmailService;

@Service
public class EmailServiceAdapter implements EmailService {

	@Override
	public void sendMail(String emial, String message) {
		FakeEmailService fakeEmailService = new FakeEmailService();
		fakeEmailService.sendMail(emial, message);

	}

	@Override
	public boolean verify(String email, String verificationCode, String otherVerificationCode) {
		FakeEmailService fakeEmailService = new FakeEmailService();
		return fakeEmailService.verifyMail(email, verificationCode, otherVerificationCode);
	}

}
