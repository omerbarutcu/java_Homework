package gun_5_odev.business.abstracts;

import gun_5_odev.entities.concretes.Verify;

public interface VerifyService {

void add(Verify verify);
	
	void delete(Verify verify);
	
	boolean verification(String eMail,String confirmCode);
}
