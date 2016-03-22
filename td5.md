# CAR - TD5
*23/03/2016*
---
> RMI = Remote Method Invocation

> Ancêtre du RMI = RPC (Remote Procedure Call)

## 1. Service de réservation

1/

Interface <- Utilisée par le client <- Service (étend l'interface java.rmi.Remote)

chaque méthode devra gérer une exception (java.rmi.exception)

Implantation <- Serveur <- Code (étend java.rmi.UnicastObject)

au moins un constructeur qui lève une exception (java.rmi.RemoteException)

3/

```java
import java.rmi.remote;
import java.rmi.RemoteException;
import ...

public interface HotItf extends remote{
  int reserver(String client, String hotel, String date, int nbChambres)
  void annuler(int numReservation) throws RemoteException;
  String lister(int numReservation) throws RemoteException;
}
```


7/

Client -> 
Agence de voyage (Client/Serveur)
  -> Compagnie Aerienne (Serveur)
  -> Hôtel (Serveur)


8/

2-Phase Commit

A --->H
|
|--------->CA

Si les deux H et CA valident on reserve. Si un ou plus ne valident pas on annule.

## 2. Patron Observateur/Sujet

1/

 - Phase abonnement / désabonnement
   + Observer est client de Subject
   + Subject est serveur d'Observer
 - Phase d'observation
   + Observer est serveur de Subject
   + Subject est client d'Observer

2/

```java
import ...

public interface Observer extends Remote{
  public void notification(String m, Subject sub) throws RemoteException;

}

public interface Subject extends Remote{
  public void attach(Observer ref) throws RemoteException;
  public void detach() throws RemoteException;
}
```

3/

```java
public class RealObserver extends UnicastRemoteObject implements Observer{
  public void notification(String m, Subject sub){
    system.out.println("modification de"+sub " : "+m);
  }
}
public class RealSubject implements Subject{
  protected List observers = new ArrayList();

  public void attach(Observer obs){
    observers.add(obs);
  }
  public void attachAll(Observer[] obs){
    Synchronized(observers){
      int i;
      for (i = 0;i<obs.length;i++)
        Observers.add(obs[i]);
    }
  }
  public void detach(Observer obs){
    Synchronized(observers){
      observers.remove(obs);
    }
  }
  public void detachAll(Observer[] obs){
    Synchronized(observers){
      int i;
      for(i=0; i<obs.length;i++)
        observers.remove(obs[i]);
    }
  }
}
```


```java
public class InstallSubject{
  public static void main(String[] args){
    Subject obj = new RealSubject();
    haming.rebind("subject",obj);
  }
}

public class InstallObserver{
  public static void main(String[] args){
    Subject obj = new RealObserver();
    haming.rebind("observer",obj);
  }
}
```

```java
public class client{
  public static void main(String[] args){
    Subject subject = (Subject)NamingLookup("subject");
    Observer observer = (Subject)NamingLookup("observer");
    Subject.attach(observer);

    for(int i= 0; i<10; i++){
      Subject.notification();
    }
    Subject.detach(observer);
  }
}
```