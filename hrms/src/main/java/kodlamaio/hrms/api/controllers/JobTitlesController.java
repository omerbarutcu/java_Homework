package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.entites.concretes.JobTitle;

@RestController
@RequestMapping("api/jobtitles")
public class JobTitlesController {
	
	private JobTitleService jobTitleService;

	@Autowired
	public JobTitlesController(JobTitleService jobTitleService) {
		super();
		this.jobTitleService = jobTitleService;
	}
	
	@GetMapping("/getall")
	public List<JobTitle> getAll(){
		return this.jobTitleService.getAll();
	}
	
	@GetMapping("/getbyid/{id}")
	public JobTitle getById(int id) {
		return this.jobTitleService.getById(id);
	}

	@GetMapping("/save")
	public Result save(JobTitle jobTitle) {
		this.jobTitleService.save(jobTitle);
		return new SuccessResult("message");
	}
}
