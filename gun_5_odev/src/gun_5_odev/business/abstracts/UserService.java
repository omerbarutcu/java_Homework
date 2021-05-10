package gun_5_odev.business.abstracts;

import java.util.List;

import gun_5_odev.entities.concretes.User;


public interface UserService {

	void add(User user);
	void update(User user);
	void delete(User user);
	User getByMail(String eMail);
	List<User> getAll();
}
