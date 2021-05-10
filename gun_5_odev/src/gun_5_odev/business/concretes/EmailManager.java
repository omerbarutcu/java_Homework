package gun_5_odev.business.concretes;

import java.util.Random;

import gun_5_odev.business.abstracts.EmailService;


public class EmailManager implements EmailService{

	@Override
	public String SendMail(String eMail) {
		
		Random rnd = new Random();
		int confirmCode = rnd.nextInt(999999);
		System.out.println("Hesap onay kodu : " + confirmCode);
		return String.valueOf(confirmCode);
	}

	@Override
	public boolean Verify(String eMail, String confirmCode) {
		
		return false;
	}

}
