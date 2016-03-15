TP2 - Plateforme REST
Durand Jean-Frédéric
Boukrou Malik


L'archive est constituée des éléments suivants :
	- un dossier src contenant les sources
	- un dossier doc contenant la javadoc
	- un dossier target contenant les fichiers compilés
	- un fichier image DiagrammeDeClasse.png


Commentaire général :
	Fonctionnel avec néanmoins quelques bug :
		- addFile ne fonctionne pas.

Détails concernant les classes :
	Le TP est principalement composé en trois parties :
		- une partie client FTP regroupant FTPCommande et FTPClient
		- une partie configuration du serveur rest regroupant les classes Config et Starter
		- une partie plateforme REST regroupant FTPResource et AdditionnalResources
	Les méthodes POST sont les suivantes :
		- LogInAction qui permet de se connecter
		- updateFile pour la mise a jour d'un contenu sur le serveur
	Une méthode DELETE :
		- deleteFile qui permet de supprimer un fichier
	Les méthdoes GET :
		- logIn, logOut connexion et deconnexion
		- getFile permet de lire les fichiers
		- addFile permet d'ajouter un fichier
Gestion des erreurs :
	- Les actions de connexions du FTPResource sont gérés via une IOException
	- Les actions du FTPClient sont gérés via des IOExceptions pour les commandes (FTPCommandes) et également FileNotFound.
