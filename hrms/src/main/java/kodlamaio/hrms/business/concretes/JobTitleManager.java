package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobTitleDao;
import kodlamaio.hrms.entites.concretes.JobTitle;

@Service
public class JobTitleManager implements JobTitleService {

	@Autowired
	private JobTitleDao jobTitleDao;

	@Override
	public DataResult<List<JobTitle>> getAll() {
		
		return new SuccessDataResult<List<JobTitle>>(jobTitleDao.findAll(), "Başarıyla listelendi");
	}

	@Override
	public ResponseEntity<Result>  add(JobTitle jobTitle) {
		if (this.jobTitleDao.existsByTitle(jobTitle.getTitle())) {
			return ResponseEntity.badRequest().body(new ErrorResult("İş alanı kullanımda!")) ;
		}
		this.jobTitleDao.save(jobTitle);
		return ResponseEntity.ok(new SuccessResult("İş eklendi"));
	}

}
