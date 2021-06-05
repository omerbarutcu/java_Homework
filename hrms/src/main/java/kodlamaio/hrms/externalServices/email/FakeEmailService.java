package kodlamaio.hrms.externalServices.email;

public class FakeEmailService {

	public void sendMail(String email, String message) {
		System.out.println("Mail sent to: " + email + "\n" + "Message: " + message);
	}

	public boolean verifyMail(String email, String verificationCode, String otherVerificationCode) {
		if (verificationCode.equals(otherVerificationCode)) {
			System.out.println(email + " Başarıyla doğrulandı");
			return true;
		} else {
			System.out.println("Doğrulama kodu eşleşmedi");
			return false;
		}
	}
}
