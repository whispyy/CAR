package akka;

import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;

public class VisitingActor extends UntypedActor {

	private List<ActorRef> childs = new ArrayList<ActorRef>();
	private String name;
	private boolean messageReceived = false;

	/**
	 * Constructeur de la classe VisitingActor.
	 *
	 * @param name : une chaine de caractère correspondant nom d'acteur.
     */
	public VisitingActor(String name) {
		this.name = name;
	}

	/**
	 * Methode onReceive() permettant de gérer la reception d'un message.
	 *
	 * @param message :  un message de type objet.
	 * @throws Exception
     */
	@Override
	public synchronized void onReceive(Object message) throws Exception {
		if (message instanceof Visiting) {
			if (!messageReceived) {
				System.out.println(name + " Reçoit la visite de : "
						+ message);
				for (ActorRef a : childs) {
					a.forward(new Visiting(name), this.getContext());
				}
				messageReceived = true;
			}
		}
		else {
			if (message instanceof AddActor) {
				childs.add(((AddActor) message).actorToAdd);
			} else {
				unhandled(message);
			}
		}
	}
}
