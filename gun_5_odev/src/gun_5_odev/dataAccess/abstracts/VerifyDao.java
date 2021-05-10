package gun_5_odev.dataAccess.abstracts;

import gun_5_odev.entities.concretes.Verify;

public interface VerifyDao {

	
void add(Verify verify);
	
	void delete(Verify verify);
	
	boolean verification(String eMail,String confirmCode);
}
