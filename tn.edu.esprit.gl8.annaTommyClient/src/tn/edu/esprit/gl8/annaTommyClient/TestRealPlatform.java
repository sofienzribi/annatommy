package tn.edu.esprit.gl8.annaTommyClient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tn.edu.esprit.gl8.annaTommyEJB.domain.Admin;
import tn.edu.esprit.gl8.annaTommyEJB.domain.Player;
import tn.edu.esprit.gl8.annaTommyEJB.domain.User;
import tn.edu.esprit.gl8.annaTommyEJB.services.interfaces.UserServicesRemote;

public class TestRealPlatform {

	private Context context;
	private UserServicesRemote userServicesRemote;

	@Before
	public void init() {
		try {
			context = new InitialContext();
			String jndiName = "ejb:tn.edu.esprit.gl8.annaTommy/tn.edu.esprit.gl8.annaTommyEJB/UserServices!"
					+ UserServicesRemote.class.getCanonicalName();
			userServicesRemote = (UserServicesRemote) context.lookup(jndiName);
		} catch (NamingException e) {
		}
	}

	@Test
	public void itShouldAddUser() {
		Admin admin = new Admin("kids");
		admin.setName("anna");
		admin.setLogin("a1");
		admin.setPassword("a1");

		Admin admin2 = new Admin("adults");
		admin2.setName("sofien");
		admin2.setLogin("a2");
		admin2.setPassword("a2");

		Player player = new Player(100);
		player.setName("moez");
		player.setLogin("p1");
		player.setPassword("p1");

		Player player2 = new Player(10);
		player2.setName("alexandre");
		player2.setLogin("p2");
		player2.setPassword("p2");

		Assert.assertTrue(userServicesRemote.addUser(admin));
		Assert.assertTrue(userServicesRemote.addUser(admin2));
		Assert.assertTrue(userServicesRemote.addUser(player));
		Assert.assertTrue(userServicesRemote.addUser(player2));
	}

	@Test
	public void itShouldLogin() {

		User userFound = userServicesRemote.login("a1", "a1");
		Assert.assertEquals("anna", userFound.getName());

	}

	@Test
	public void itShouldFindAllPlayers() {

		Assert.assertEquals(2, userServicesRemote.findAllPlayers().size());

	}

	@Test
	public void itShouldDeletePlayer() {

		Assert.assertTrue(userServicesRemote.deleteUserById(1));

	}

}
