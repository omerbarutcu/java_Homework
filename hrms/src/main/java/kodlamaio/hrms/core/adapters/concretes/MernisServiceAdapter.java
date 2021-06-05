package kodlamaio.hrms.core.adapters.concretes;

import java.util.Date;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.core.adapters.abstracts.UserCheckService;
import kodlamaio.hrms.externalServices.mernis.FakeMernisService;

@Service
public class MernisServiceAdapter implements UserCheckService {

	@Override
	public boolean validate(String nationalId, String firstName, String lastName, Date date) {
		FakeMernisService clint = new FakeMernisService();
		boolean result = clint.TCKimlikDogrula(nationalId, firstName, lastName, date);
		return result;
	}

}
