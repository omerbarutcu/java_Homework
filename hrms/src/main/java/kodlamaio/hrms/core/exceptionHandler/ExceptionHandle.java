package kodlamaio.hrms.core.exceptionHandler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kodlamaio.hrms.core.utilities.results.ErrorDataResult;

@ControllerAdvice
public class ExceptionHandle {

	/*
	 * @ExceptionHandler(MethodArgumentNotValidException.class)
	 * 
	 * @ResponseStatus(HttpStatus.BAD_REQUEST) public ErrorDataResult<Object>
	 * handleValidationException(MethodArgumentNotValidException exceptions) {
	 * Map<String, String> validationErrors = new HashMap<String, String>(); for
	 * (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
	 * validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
	 * } ErrorDataResult<Object> errors = new
	 * ErrorDataResult<Object>(validationErrors, "Doğrulama hatası"); return errors;
	 * }
	 */

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDataResult<Object>> handleValidationExceptions(
			MethodArgumentNotValidException exceptions) {
		Map<String, String> validationErrors = new HashMap<>();

		exceptions.getBindingResult().getFieldErrors()
				.forEach(fieldError -> validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));

		return ResponseEntity.badRequest().body(new ErrorDataResult<>(validationErrors, "Doğrulama hataları"));
	}

}
