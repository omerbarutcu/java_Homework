package kodlamaio.hrms.core.utilities.results;

import java.util.List;

public class ResultChecker {

	public static Result check(List<Result> results) {
		for (Result result : results) {
			if (!result.isSuccess()) {
				return result;
			}
		}
		return new SuccessResult();
	}
}
