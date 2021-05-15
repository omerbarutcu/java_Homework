package kodlamaio.hrms.core.utilities.result;

public class ErrorResult implements Result{

	private boolean success;
	private String message;
	
	public ErrorResult() {
		this.success=false;
	}
	
	public ErrorResult(String message) {
		this();
		this.message = message;
	}

	@Override
	public boolean isSuccess() {
		
		return success;
	}

	@Override
	public String getMessage() {
		
		return message;
	}

}
