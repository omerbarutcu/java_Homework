package kodlamaio.hrms.dataAccess.abstracts;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entites.concretes.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByUuid(UUID uuid);
}
