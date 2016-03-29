TP3 - AKKA
DURAND Jean-Frédéric
BOUKROU Malik

Description du package :
	- un dossier doc contient la doc
	- un dossier src contient les sources (dans un package nommé akka)
		- VisitingActor
		- Visiting
		- AddActor
		- Main
	- le diagramme de classe est à la racine
	/!\ les librairies sont a placer à la racine dans un dossier lib. Elles ont été retirés pour des questions de places.


Description des classes :
	- voir doc/index.html
	- voir diagramme de classe.

Commentaire :
	La classe Main de l'archive contient la réponses aux différentes questions à coder.

Scénarios de tests :
	- Si un acteur reçoit un message de type indeterminé rien ne se passe.
	- Si un acteur reçoit un message de type AddActor alors un nouvel acteur est ajouté à la liste des fils de l'acteur
	- Si le parent reçoit un message Visiting, alors il l'envoie à tous ses fils.
	- Si un acteur est fils et parent en même temps, alors l'acteur parent l'envoie au fils qui l'envoie au parent qui n'en tiendra pas compte.
		cf commentaire dans le Main.
