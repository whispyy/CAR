package akka;

import java.io.Serializable;

import akka.actor.ActorRef;

public class AddActor implements Serializable {
	protected ActorRef actorToAdd;

	/**
	 * Constructeur de la classe AddActor
	 *
	 * @param actorRef : un acteur à ajouter
     */
	public AddActor(ActorRef actorRef) {
		this.actorToAdd = actorRef;
	}

	/**
	 * Methode toString() permettant de renvoyer un acteur sous forme
	 * d'une chaine de caractère
	 *
	 * @return une chaine de caractère correspondant à l'acteur.s
     */
	@Override
	public String toString() {
		return actorToAdd.toString();
	}

}
