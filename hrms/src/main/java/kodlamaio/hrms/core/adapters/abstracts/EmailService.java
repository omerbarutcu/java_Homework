package kodlamaio.hrms.core.adapters.abstracts;

public interface EmailService {

	void sendMail(String emial, String message);

	boolean verify(String email, String verificationCode, String otherVerificationCode);
}
