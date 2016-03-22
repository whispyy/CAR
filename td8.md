# CAR - TD8
*15/03/2016*
---

## Test d'arrêt de la propagation

1) On est pas capable de prédire si les deux vagues vont toujours se rencontrer aux antipodes de l'initiateur.

Pour qu'ils se rencontrent aux antipodes ils nous faut deux hypotheses fortes :

- Les serveurs fonctionnent à la même vitesse tout le temps.
- Le réseau fonctionne de manière symétrique.

Comportement non déterministe, on ne peut pas supposer ceci donc.

2) Exemple à 4 noeuds : k --sens1--> i --- j <--sens2-- l

En parallèle :

- 1/ Le site i a reçu la visite du sens 1 avant la visite du sens 2
- 2/ Le site i propage la visite du sens 1 vers le site j et il termine la propagation du sens 2
- 1/ Le site j a reçu le visite du sens  2 avant la visite du sens 1
- 2/ Le site j propage la visite du sens 2 vers le site i et il termine la propagation du sens 1

Conséquence :

- Aucun site k en amont de i vers le sens 1 ne recevra la visite du sens 2
- Aucun site l en amont de j vers le sens 2 ne recevra la visite du sens 1

## Echanges de messages

3) 

## Evaluation des performances

4) En partant des hypothèses du début et en considérant que les processeurs sont tous identiques :

- Le nombre de message sera à peu près divisé par deux à un ou deux prêt (correspondants aux noeuds de croisements des deux vagues).
- En temps d'exécution également, seuls les serveurs aux antipodes auront un peu plus de calculs.

## Interfaces 

5) 
```java
Public interface Election extends java.rmi.Remote{
  public static final int SENS1 = 0;
  public static final int SENS2 = 1;
  public void FixerVoisinDroite(election other) throws RemoteException;
  public void FixerVoisinGauche(election other) throws RemoteException;
  public int election(int num, int sens);
  public void initiateur() throws RemoteException;
}
```

# Implantation d'un objet dans l'anneau

7)
```java
Public class ElectionImpl extends UnicastRemoteObject implements Election{
  private int id;
  private Election voisin_g, voisin_d;
  private visite = false;
  private int max;

  public void init(int id, election voisin_g, election voisin_d) throws RemoteException{
    this.id = id;
    this.voisin_g = voisin_g;
    this.voisin_d = voisin_d;
  }

  public int election(int max, int sens) throws RemoteException{
    synchronized(this){
      if (id > max)
        this.max = id;
      else
        this.max = max;
      if (visited == true)
        return this.max;
      else
        visited = true;
    }
    /*
    On arrête la synchronisation ici pour éviter le blocage au moment du croisement des deux vagues aux antipodes.
    */
    Election suivant = (sens == SENS1 ? voisin_g:voisin_d);
    return suivant.election(this.max,sens);
  }

  public void initiateur()throws RemoteException{
    Vague vague1 = new vague(voisin_g, SENS1);
    Vague vague2 = new vague(voisin_g, SENS2);
    vague1.join();
    vague2.join();
    int resultVote = Math.max(vague1.Result(), vague2.Result());
    System.out.println("reçu :"+vague1.Result()+"-"+vague2.Result());
  }
}
```

```java
Private class Vague extends Thread{
  private Election e;
  private int sens;
  private int res;

  public Vague(Election e, int sens){
    this.e = e;
    this.sens = sens;
    this.start();
  }

  public void num(){
    try
      res = e.election(IO,sens);
    catch 
      (RemoteException ex);
  }
}
```