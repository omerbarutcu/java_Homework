package gun_5_odev.business.abstracts;

import gun_5_odev.entities.concretes.User;

public interface AuthService {

	boolean signIn(User user);

	boolean signInWithGoogle(User user);

	boolean signUp(User user);

	boolean signOut(User user);

	boolean isValidEmailAddress(String email);

	boolean mailConfirmation(String email, String confirmCode);
}
