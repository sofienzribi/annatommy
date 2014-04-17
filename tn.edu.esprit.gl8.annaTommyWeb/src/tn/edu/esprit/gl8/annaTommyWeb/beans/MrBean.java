package tn.edu.esprit.gl8.annaTommyWeb.beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import tn.edu.esprit.gl8.annaTommyEJB.domain.Admin;
import tn.edu.esprit.gl8.annaTommyEJB.domain.Player;
import tn.edu.esprit.gl8.annaTommyEJB.domain.User;
import tn.edu.esprit.gl8.annaTommyEJB.services.interfaces.UserServicesLocal;

@ManagedBean(name = "beanbean")
@SessionScoped
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
	public String doAddUser() {
		userServicesLocal.addUser(player);
		formOneVisibility = false;
		return "";

	}

	public String doDeleteUser() {
		Player playerSelected = dataModel.getRowData();
		userServicesLocal.deleteUserById(playerSelected.getId());
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
		players = userServicesLocal.findAllPlayers();

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

}
