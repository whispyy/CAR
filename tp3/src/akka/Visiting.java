package akka;

import java.io.Serializable;

public class Visiting implements Serializable {
	private String who;

	/**
	 * Constructeur de la classe Visiting
	 *
	 * @param who : une chaine de caractère correspondant
	 * à un acteur entrant dans le visiteur.
     */
	public Visiting(String who) {
		this.who = who;
	}

	/**
	 * Methode toString() renvoyant l'acteur entrant.
	 *
	 * @return une chaine de caractère.
     */
	@Override
	public String toString() {
		return who;
	}
}
