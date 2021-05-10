package gun_5_odev;

import gun_5_odev.business.abstracts.AuthService;
import gun_5_odev.business.abstracts.EmailService;
import gun_5_odev.business.abstracts.UserService;
import gun_5_odev.business.abstracts.VerifyService;
import gun_5_odev.business.concretes.AuthManager;
import gun_5_odev.business.concretes.EmailManager;
import gun_5_odev.business.concretes.UserManager;
import gun_5_odev.business.concretes.VerifyManager;
import gun_5_odev.core.GoogleAuthManagerAdapter;
import gun_5_odev.dataAccess.concretes.InMemoryUserDao;
import gun_5_odev.dataAccess.concretes.InMemoryVerifyDao;
import gun_5_odev.entities.concretes.User;
import gun_5_odev.entities.concretes.Verify;

public class Main {

	public static void main(String[] args) {
		

		// yeni kullanýcý
		
				User user = new User(1, "Omer", "Barutcu", "test@hotmail.com", "123456", false);

				// onay kodu mail yolu ile gönderiliyor
				
				EmailService emailService = new EmailManager();
				String confirmCode = emailService.SendMail(user.geteMail());

				// onay kodu fake veritabanýna ekleniyor
				
				Verify verify = new Verify(user.geteMail(), confirmCode);
				VerifyService verifyService = new VerifyManager(new InMemoryVerifyDao());
				verifyService.add(verify);

				UserService userService = new UserManager(new InMemoryUserDao());
				AuthService authService = new AuthManager(userService, verifyService, new GoogleAuthManagerAdapter());
				authService.signUp(user); // yeni kullanýcý kayýt oluyor

				// yeni kullanýcý onay kodunu giriyor
				
				authService.mailConfirmation(user.geteMail(), confirmCode);

				authService.signIn(user); // sisteme giriþ yapýyor
				authService.signOut(user); // sistemden çýkýþ yapýyor

				// google kullanýcýsý
				User googleUser = new User(2, "google", "user", "googleuser@gmail.com", "", false);

				// google hesabý ile sisteme giriþ yapýyor
				authService.signInWithGoogle(googleUser);

	}

}
