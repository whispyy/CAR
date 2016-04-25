package car.tp4;

import java.util.ArrayList;

import javax.ejb.Stateful;

@Stateful
public class ListeCommandes {

	private ArrayList<Commande> commandes;
	
	public ListeCommandes() {
		this.commandes = new ArrayList<Commande>();
	}
	
	public void add(Commande commande) {
		this.commandes.add(commande);
	}

	public ArrayList<Commande> getListeCommandes() {
		return this.commandes;
	}

}
