package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.core.utilities.result.Result;
import kodlamaio.hrms.core.utilities.result.SuccessResult;
import kodlamaio.hrms.entites.concretes.Candidate;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {

	private CandidateService candidateService;
	
	@Autowired
	public CandidatesController(CandidateService candidateService) {
		super();
		this.candidateService = candidateService;
	}

	@GetMapping("/getall")
	public List<Candidate> getAll(){
		return this.candidateService.getAll(); 
	}
	
	@GetMapping("/getbyid/{id}")
	public Candidate getById(int id) {
		return this.candidateService.getById(id);
	}
	
	@GetMapping("/save")
	public Result save(Candidate candidate) {
		this.candidateService.save(candidate);
		return new SuccessResult("message");
	}
}
