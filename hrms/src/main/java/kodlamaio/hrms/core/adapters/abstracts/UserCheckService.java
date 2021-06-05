package kodlamaio.hrms.core.adapters.abstracts;

import java.util.Date;

public interface UserCheckService {

	public boolean validate(String nationalId, String firstName, String lastName, Date date);
}
