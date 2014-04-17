package tn.edu.esprit.gl8.annaTommyEJB.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.edu.esprit.gl8.annaTommyEJB.domain.Player;
import tn.edu.esprit.gl8.annaTommyEJB.domain.User;
import tn.edu.esprit.gl8.annaTommyEJB.services.interfaces.UserServicesLocal;
import tn.edu.esprit.gl8.annaTommyEJB.services.interfaces.UserServicesRemote;

/**
 * Session Bean implementation class UserServices
 */
@Stateless
public class UserServices implements UserServicesRemote, UserServicesLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserServices() {
		// TODO Auto-generated constructor stub
	}

	public boolean addUser(User user) {
		boolean b = false;
		try {
			entityManager.persist(user);
			b = true;
		} catch (Exception e) {
			System.err.println("emmmm ...");
		}
		return b;
	}

	public User login(String login, String password) {
		User userFound = null;
		String jpql = "select u from User u where u.login=:param1 and u.password=:param2";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", login);
		query.setParameter("param2", password);

		try {
			userFound = (User) query.getSingleResult();
		} catch (Exception e) {
			System.err.println("please try again ...");
		}

		return userFound;
	}

	@Override
	public List<Player> findAllPlayers() {
		return entityManager.createQuery("select p from Player p")
				.getResultList();

	}

	@Override
	public boolean deleteUserById(int id) {
		boolean b = false;
		try {
			entityManager.remove(entityManager.find(User.class, id));
			b = true;

		} catch (Exception e) {
			System.err.println("defeat !!!!...");
		}
		return b;
	}

	@Override
	public boolean updateUser(User user) {

		boolean b = false;
		try {
			entityManager.merge(user);
			b = true;
		} catch (Exception e) {
			System.err.println("emmmmm3.........");
		}
		return b;
	}

	@Override
	public User findUserById(int id) {

		return entityManager.find(User.class, id);
	}

}
