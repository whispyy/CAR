# CAR - TD5
---
> RMI = Remote Method Invocation

> Ancêtre du RMI = RPC (Remote Procedure Call)

## 1. Service de réservation

1/

Interface <- Utilisée par le client <- Service (étend l'interface java.rmi.Remote)

chaque méthode devra gérer une exception (java.rmi.exception)

Implantation <- Serveur <- Code (étend java.rmi.UnicastObject)

au moins un constructeur qui lève une exception (java.rmi.RemoteException)

2/

```java
import java.rmi.remote;
import java.rmi.RemoteException;

public interface HotItf extends remote{
  int reserver(String client, String hotel, String date, int nbChambres)
  void annuler(int numReservation) throws RemoteException;
  String lister(int numReservation) throws RemoteException;

}
```
