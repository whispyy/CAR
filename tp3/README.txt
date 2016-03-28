CAR - TP3 - AKKA

Scénarios :
	
	Scénario : 
		On possède un acteur parent qui possède une liste de fils qui possèdent chacun une liste de fils (Qui peut être vide)
		Quand le parent reçois un message de type Greeting
		Alors le parent envois ce message a tous ses fils

	Scénario : 
		On possède un acteur.
		Quand l'acteur reçois un message de type AddActor
		Alors un nouvel acteur est ajouté a la liste des fils de l'acteur

	Scénario :
		On possède un acteur d'un systeme 1 et un acteur d'un système 2
		Quand on ajoute l'acteur du système 2 aux fils de l'acteur du système 1
		Alors l'acteur du systeme 1 peut transferer ses messages vers le fils du système 2 sans soucis

	Scénario :
		On possède un acteur.
		Quand il reçois un message de type indeterminé
		Alors rien ne se passe

	Scénario :
		On possède un acteur 1 qui est fils de l'acteur 2 et inversement
		Quand l'acteur 1 reçoit un message de type Greeting
		Alors il est transféré a l'acteur 2 qui l'envois a l'acteur 1 qui ne le prend pas en compte pour éviter une boucle infini

	Scénario : 
