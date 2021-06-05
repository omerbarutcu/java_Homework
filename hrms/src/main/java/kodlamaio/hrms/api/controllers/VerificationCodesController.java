package kodlamaio.hrms.api.controllers;

import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.VerificationCodeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entites.concretes.VerificationCode;

@RestController
@RequestMapping("/api/verification")
public class VerificationCodesController {

	@Autowired
	private VerificationCodeService verificationCodeService;

	@GetMapping("/confirm/{uuid}/{code}")
	public ResponseEntity<Result> confirm(@PathVariable("uuid") UUID uuid,
			@PathVariable("code") String verificationCode) {
		ResponseEntity<DataResult<VerificationCode>> result = verificationCodeService.findByUserUuid(uuid);
		DataResult<VerificationCode> body = result.getBody();
		if (body != null) {
			if (!body.isSuccess()) {
				return ResponseEntity.badRequest().body(new ErrorResult(body.getMessage()));
			} else if (body.getData().isVerified()) {
				return ResponseEntity.badRequest().body(new ErrorResult("Zaten onaylı"));
			} else {
				return verificationCodeService.confirm(body.getData(), verificationCode);
			}

		} else {
			return ResponseEntity.badRequest().body(new ErrorResult("Hata! kod boş"));
		}
	}
}
