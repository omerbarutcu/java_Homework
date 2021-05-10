package gun_5_odev.business.concretes;

import gun_5_odev.business.abstracts.VerifyService;
import gun_5_odev.dataAccess.abstracts.VerifyDao;
import gun_5_odev.entities.concretes.Verify;

public class VerifyManager implements VerifyService{

	private VerifyDao verifyDao;
	
	
	public VerifyManager(VerifyDao verifyDao) {
		super();
		this.verifyDao = verifyDao;
	}

	@Override
	public void add(Verify verify) {
		this.verifyDao.add(verify);
		
	}

	@Override
	public void delete(Verify verify) {
		this.verifyDao.delete(verify);
		
	}

	@Override
	public boolean verification(String eMail, String confirmCode) {
		
		return this.verifyDao.verification(eMail, confirmCode);
	}

}
