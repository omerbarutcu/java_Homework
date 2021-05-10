package gun_5_odev.business.concretes;

import java.util.List;

import gun_5_odev.business.abstracts.UserService;
import gun_5_odev.dataAccess.abstracts.UserDao;
import gun_5_odev.entities.concretes.User;


public class UserManager implements UserService{
	
	private UserDao userDao;

	public UserManager(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	@Override
	public void add(User user) {
		userDao.add(user);
		System.out.println("Kullanýcý eklendi : " + user.getFirstName());
	}

	@Override
	public void update(User user) {
		userDao.update(user);
		
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
		
	}


	@Override
	public User getByMail(String eMail) {
		
		return userDao.getByMail(eMail);
	}

	@Override
	public List<User> getAll() {
		
		return userDao.getAll();
	}

}
