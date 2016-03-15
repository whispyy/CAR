TP2 - Plateforme REST
Durand Jean-Fr�d�ric
Boukrou Malik


L'archive est constitu�e des �l�ments suivants :
	- un dossier src contenant les sources
	- un dossier doc contenant la javadoc
	- un dossier target contenant les fichiers compil�s
	- un fichier image DiagrammeDeClasse.png


Commentaire g�n�ral :
	Fonctionnel avec n�anmoins quelques bug :
		- addFile ne fonctionne pas.

D�tails concernant les classes :
	Le TP est principalement compos� en trois parties :
		- une partie client FTP regroupant FTPCommande et FTPClient
		- une partie configuration du serveur rest regroupant les classes Config et Starter
		- une partie plateforme REST regroupant FTPResource et AdditionnalResources
	Les m�thodes POST sont les suivantes :
		- LogInAction qui permet de se connecter
		- updateFile pour la mise a jour d'un contenu sur le serveur
	Une m�thode DELETE :
		- deleteFile qui permet de supprimer un fichier
	Les m�thdoes GET :
		- logIn, logOut connexion et deconnexion
		- getFile permet de lire les fichiers
		- addFile permet d'ajouter un fichier
Gestion des erreurs :
	- Les actions de connexions du FTPResource sont g�r�s via une IOException
	- Les actions du FTPClient sont g�r�s via des IOExceptions pour les commandes (FTPCommandes) et �galement FileNotFound.
