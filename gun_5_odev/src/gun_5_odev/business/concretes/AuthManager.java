package gun_5_odev.business.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import gun_5_odev.business.abstracts.AuthService;
import gun_5_odev.business.abstracts.UserService;
import gun_5_odev.business.abstracts.VerifyService;
import gun_5_odev.core.GoogleAuthManagerAdapter;
import gun_5_odev.entities.concretes.User;


public class AuthManager implements AuthService{

	private UserService userService;
	private VerifyService verifyService;
	private GoogleAuthManagerAdapter googleAuthManagerAdapter;
	
	
	public AuthManager(UserService userService, VerifyService verifyService,
			GoogleAuthManagerAdapter googleAuthManagerAdapter) {
		
		this.userService = userService;
		this.verifyService = verifyService;
		this.googleAuthManagerAdapter = googleAuthManagerAdapter;
	}

	@Override
	public boolean signIn(User user) {
		
		if (user.geteMail() == "") {
			System.out.println("E-mail girmediniz!");
			return false;
		}

		if (user.getPassword() == "") {
			System.out.println("Parola girmediniz!");
			return false;
		}

		User result = this.userService.getByMail(user.geteMail());

		if (result == null) {
			System.out.println("Kullan�c� bulunamad�");
			return false;
		}
		if (!user.getPassword().equals(result.getPassword())) {
			System.out.println("Parola yanl��");
			return false;
		}

		if (!user.isVerified()) {
			System.out.println("Kullan�c� hen�z do�rulanmad�");
			return false;
		}

		System.out.println("Sisteme giri� yap�ld�");
		return true;
	}

	@Override
	public boolean signInWithGoogle(User user) {
		
		googleAuthManagerAdapter.login(user.geteMail());

		User result = this.userService.getByMail(user.geteMail());
		if (result == null) {
			user.setVerified(true);
			this.userService.add(user);
		}
		return true;
	}

	@Override
	public boolean signUp(User user) {
		
		if (this.userService.getByMail(user.geteMail()) != null) {
			System.out.println("E-mail sistemde kay�tl�!");
			return false;
		}

		if (!this.isValidEmailAddress(user.geteMail())) {
			System.out.println("E-mail format� hatal�!");
			return false;
		}

		if (user.getFirstName().length() < 2) {
			System.out.println("Kullan�c� ad� en az 2 karakter olmal�!");
			return false;
		}

		if (user.getLastName().length() < 2) {
			System.out.println("Kullan�c� soyad� en az 2 karakter olmal�!");
			return false;
		}

		if (user.getPassword().length() < 6) {
			System.out.println("Parola en az 6 karakter olmal�!");
			return false;
		}

		user.setVerified(false);
		this.userService.add(user);

		return true;
	}

	@Override
	public boolean signOut(User user) {
		
		System.out.println("Sistemden ��k�� yap�ld�");
		return false;
	}

	@Override
	public boolean isValidEmailAddress(String email) {
		
		Pattern p = Pattern.compile("^(.+)@(.+)$");
		Matcher m = p.matcher(email);
		if (!m.matches()) {
			return false;
		}
		return true;
	}

	@Override
	public boolean mailConfirmation(String email, String confirmCode) {
		
		boolean result = this.verifyService.verification(email, confirmCode);
		if (!result) {
			System.out.println("Kay�t do�rulama hatal�");
			return false;
		}

		System.out.println("Kay�t do�rulama ba�ar�l�");

		User user = this.userService.getByMail(email);
		user.setVerified(true);
		this.userService.update(user);

		return true;
	}

}
