package akka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class Main {

	/**
	 * La classe correspondant à la modélisation du TP.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ActorSystem system1 = ActorSystem.create("MySystem1");
		ActorSystem system2 = ActorSystem.create("MySystem2");

		ActorRef visiter1, visiter2, visiter3, visiter4, visiter5, visiter6;

		visiter1 = system1.actorOf(
				Props.create(VisitingActor.class, "Acteur 1"), "visiter1");
		visiter2 = system2.actorOf(
				Props.create(VisitingActor.class, "Acteur 2"), "visiter2");
		visiter3 = system2.actorOf(
				Props.create(VisitingActor.class, "Acteur 3"), "visiter3");
		visiter4 = system2.actorOf(
				Props.create(VisitingActor.class, "Acteur 4"), "visiter4");
		visiter5 = system1.actorOf(
				Props.create(VisitingActor.class, "Acteur 5"), "visiter5");
		visiter6 = system1.actorOf(
				Props.create(VisitingActor.class, "Acteur 6"), "visiter6");

		visiter1.tell(new AddActor(visiter2), ActorRef.noSender());
		visiter1.tell(new AddActor(visiter5), ActorRef.noSender());
		visiter2.tell(new AddActor(visiter3), ActorRef.noSender());
		visiter2.tell(new AddActor(visiter4), ActorRef.noSender());
		visiter5.tell(new AddActor(visiter6), ActorRef.noSender());

		// Question 5
		visiter4.tell(new AddActor(visiter6), ActorRef.noSender());

		// Cas afin de verifier qu'on empêche les boucles infinies
		visiter3.tell(new AddActor(visiter1), ActorRef.noSender());

		// Envoie des messages
		System.out.println("Départ à partir de l'acteur 1");
		visiter2.tell(new Visiting("Main"), ActorRef.noSender());

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		system1.shutdown();
		system2.shutdown();
	}
}
