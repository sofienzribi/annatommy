package tn.edu.esprit.gl8.annaTommyWeb.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import tn.edu.esprit.gl8.annaTommyEJB.domain.Admin;
import tn.edu.esprit.gl8.annaTommyEJB.domain.Player;
import tn.edu.esprit.gl8.annaTommyEJB.domain.User;
import tn.edu.esprit.gl8.annaTommyEJB.services.interfaces.UserServicesLocal;

@ManagedBean(name = "beanbean")
@ViewScoped
public class MrBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// the model
	private User user = new User();
	private Player player = new Player();
	private List<Player> players;
	private boolean formOneVisibility = false;
	private DataModel<Player> dataModel = new ListDataModel<Player>();
	// injection of the proxy
	@EJB
	private UserServicesLocal userServicesLocal;

	// the methods
	public String selectPlayer() {

		formOneVisibility = true;
		return null;
	}

	public String doAddUser() {
		userServicesLocal.updateUser(player);
		formOneVisibility = false;
		System.out.println("ok");
		player = new Player();
		init();
		return "";

	}

	public String doUpdateUser() {

		userServicesLocal.updateUser(player);
		formOneVisibility = false;
		init();
		return "";
	}

	public String doDeleteUser() {
		userServicesLocal.deleteUserById(player.getId());
		player = new Player();
		formOneVisibility = false;
		init();
		return "";
	}

	public String modifyVisibility() {

		formOneVisibility = true;
		return "";
	}

	public String doLogin() {
		User userFound = userServicesLocal.login(user.getLogin(),
				user.getPassword());
		if (userFound != null) {
			if (userFound instanceof Admin) {
				user = userFound;
				return "/pages/admin/admin.jsf";

			} else {
				user = userFound;
				return "/pages/player/room.jsf";
			}

		} else {
			return "error.jsf";
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public List<Player> getPlayers() {

		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public boolean isFormOneVisibility() {
		return formOneVisibility;
	}

	public void setFormOneVisibility(boolean formOneVisibility) {
		this.formOneVisibility = formOneVisibility;
	}

	public DataModel<Player> getDataModel() {
		dataModel.setWrappedData(userServicesLocal.findAllPlayers());
		return dataModel;
	}

	public void setDataModel(DataModel<Player> dataModel) {
		this.dataModel = dataModel;
	}

	@PostConstruct
	public void init() {
		players = userServicesLocal.findAllPlayers();

	}

}
