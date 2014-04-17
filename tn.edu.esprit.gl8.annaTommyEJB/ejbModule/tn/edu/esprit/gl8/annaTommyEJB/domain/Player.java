package tn.edu.esprit.gl8.annaTommyEJB.domain;

import java.io.Serializable;
import javax.persistence.*;
import tn.edu.esprit.gl8.annaTommyEJB.domain.User;

/**
 * Entity implementation class for Entity: Player
 *
 */
@Entity

public class Player extends User implements Serializable {

	
	private int token;
	private static final long serialVersionUID = 1L;

	public Player() {
		super();
	}   
	
	public Player(int token) {
		super();
		this.token = token;
	}

	public int getToken() {
		return this.token;
	}

	public void setToken(int token) {
		this.token = token;
	}
   
}
