package kodlamaio.hrms.dataAccess.abstracts;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entites.concretes.EmployerVerify;

public interface EmployerVerifyDao extends JpaRepository<EmployerVerify, Integer> {

	Optional<EmployerVerify> findByEmployerUuid(UUID uuid);

	boolean existsByEmployerUuidAndConfirmedTrue(UUID uuid);
}
