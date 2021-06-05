package kodlamaio.hrms.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entites.concretes.JobTitle;

@RestController
@RequestMapping("api/jobtitles")
public class JobTitlesController {

	@Autowired
	private JobTitleService jobTitleService;

	@GetMapping("/getall")
	public  DataResult<List<JobTitle>> getAll() {
		return this.jobTitleService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<?> add(@Valid @RequestBody JobTitle jobTitle) {
		return ResponseEntity.ok(jobTitleService.add(jobTitle));
	}

}
