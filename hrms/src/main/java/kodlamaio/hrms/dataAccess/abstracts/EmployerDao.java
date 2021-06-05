package kodlamaio.hrms.dataAccess.abstracts;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entites.concretes.Employer;

public interface EmployerDao extends JpaRepository<Employer, Integer> {

	Optional<Employer> findByUuid(UUID uuid);

	boolean existsById(int id);
}
