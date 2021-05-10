package gun_5_odev.core;

import gun_5_odev.google.GoogleAuthenticationManager;

public class GoogleAuthManagerAdapter implements GoogleAuthService{

	@Override
	public void login(String eMail) {
		GoogleAuthenticationManager googleAuthenticationManager=new GoogleAuthenticationManager();
		googleAuthenticationManager.login(eMail);
		
	}

}
