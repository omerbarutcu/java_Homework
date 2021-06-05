package kodlamaio.hrms.core.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.core.entites.User;

public interface UserDao extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);
}
