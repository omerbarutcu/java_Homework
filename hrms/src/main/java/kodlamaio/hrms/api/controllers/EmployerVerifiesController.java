package kodlamaio.hrms.api.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerVerifyService;
import kodlamaio.hrms.core.utilities.results.Result;

@RestController
@RequestMapping("/api/employerverify")
public class EmployerVerifiesController {

	@Autowired
	private EmployerVerifyService employerVerifyService;

	@PostMapping("/verify{employerUuid}/{employeeUuid}")
	public ResponseEntity<Result> personelVerify(@PathVariable("employerUuid") UUID employerUuid,
			@PathVariable("employeeUuid") UUID personnelUuid) {

		return employerVerifyService.verifyEmployer(employerUuid, personnelUuid);
	}

}
