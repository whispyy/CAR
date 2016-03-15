# CAR - TD7
---

1) On fait passer dans chaque message le numéro de l'initiateur du message. On s'arrête lorsque l'on arrive au prédecesseur de cet initiateur.

2) On propose d'envoyer un couple (mon numero, numero initiateur)

3) On doit faire un tour pour déterminer l'élu (= le plus grand identifiant), puis refaire un tour pour le faire savoir à tout le monde. Ne pas oublier d'inclure son identifiant dans le message.

4) **Préconditions :** 
	- on tourne toujours dans le même sens
	- les numéros sont différents et dans l'ordre
	- il n'y a qu'un initiateur
	
**Algo :** Tant que l'on est pas revenu a l'initiateur
	 				 	envoie (numero initiateur, mon numero || numero recu si plus grand que mon numero)

5) Apres le premier tour :

**Algo :** On propage à tous les utilisateurs le message en refaisant un tour.

6) **asynchrone :** on envoie un message qui envoie un message au suivant, ...etc. On refait donc un tour.

**synchrone :** arrivé au précédent de l'initiateur, on repart dans le sens inverse pour renvoyer le message au précédent. On évite ainsi de faire un tour complet en plus.

7) 
```java
Public interface Participant extends java.rmi.Remote{
	public int election(int max, int init);
}

Public class ParticipantImpl extends UnicastRemoteOject implements Participant{

	//identifiant de site
	protected int noInterne;
	//site suivant
	protected Participant suivantInterne;

	//init anneau
	public ParticipantImpl(int no, Participant suivant)throws java.rmi.RemoteException{
		this.noInterne = no;
		this.suivantInterne = suivant;

		public int election(int max, int init) throws RemoteException{
			if (max < noInterne)
				max = noInterne;
			if (suivantInterne = suivant)
				return max;
			else
				return suivantInterne.election(max,init);
		}
	}
```

