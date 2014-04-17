package tn.edu.esprit.gl8.annaTommyEJB.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.edu.esprit.gl8.annaTommyEJB.domain.Player;
import tn.edu.esprit.gl8.annaTommyEJB.domain.User;

@Local
public interface UserServicesLocal {
	boolean addUser(User user);

	User login(String login, String password);

	List<Player> findAllPlayers();
	
	boolean deleteUserById(int id);
	
	boolean updateUser(User user);
	
	User findUserById(int id);

}
