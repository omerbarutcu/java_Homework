package gun_5_odev.dataAccess.abstracts;

import java.util.List;

import gun_5_odev.entities.concretes.User;


public interface UserDao {

	void add(User user);
	void update(User user);
	void delete(User user);
	User getById(int id);
	User getByMail(String eMail);
	List<User> getAll();
}
