# CAR - TD9
*29/03/2016*
---

## Interfaces

1) Utilisateurs -> Client (Demande un service)
   Aiguilleur -> Serveur/Client (Offre un service d'aiguillage, Demande un service de calcul)
   Machines -> Serveur (Offre un service)

2) 

```IDL
typedef binary Donnees List<types>

Service Machine{
  Donnees lecture(1:string nomFichier);
  bool ecriture(1:string nomFichier, 2:Donnees d);
}
```

## Aiguilleur

3) Interfaces identique mais traitement différents.

4)

```IDL
Service Controle{
  bool ajouterMachine(1:Donnees adresse);
  bool retirerMachine(1:Donnees adresse);
}
```

5)

6) oneway :
```IDL
void charge(1:nomMachine machine, 2: 123 chargeMachine);
```

7) Pas de notion d'héritage multiple, je mets donc toutes les méthodes dans le Service Machine.

## Concurrence des traitements

8) 1 écriture OU exclusif N lectures.

9)
```IDL
enum Service(utilisateurF, machine);

Service Machine{
  Donnees lecture(1:string nF);
  bool ecriture(1:string nF, 2:Donnees d, 3:source src);
}
```

10) J'oblige à ce que les requêtes soient faites dans le même ordre sur toutes les machines. Bloquage au niveau de l'aiguillage.

## Performances

11) La machine pourrait très bien répondre directement à l'utilisateur.

12) Tout le monde devient Client/Serveur avec cette solution.

13)
```IDL
```