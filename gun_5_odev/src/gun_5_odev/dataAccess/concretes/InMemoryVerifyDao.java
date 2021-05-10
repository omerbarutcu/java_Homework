package gun_5_odev.dataAccess.concretes;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import gun_5_odev.dataAccess.abstracts.VerifyDao;
import gun_5_odev.entities.concretes.Verify;


public class InMemoryVerifyDao implements VerifyDao{

	
	private List<Verify> verifies;
	
	public InMemoryVerifyDao() {
		this.verifies=new ArrayList<Verify>();
		
	}
	
	
	@Override
	public void add(Verify verify) {
		this.verifies.add(verify);
		
	}

	@Override
	public void delete(Verify verify) {
		int result= this.verifies.indexOf(verify);
		this.verifies.remove(result);
		
	}

	@Override
	public boolean verification(String eMail, String confirmCode) {
		
		Verify result=this.verifies.stream().filter((v) -> v.geteMail() == eMail && v.getComfirmCode()== confirmCode)
				.findFirst().orElse(null);
		if (result!=null) {
			Instant start= result.getExpriation().toInstant();
			Instant end=Instant.now();
			long diff =Duration.between(end, start).toMinutes();
			if (diff>0) {
				return true;
			}
		}
		
		
		return false;
	}

}