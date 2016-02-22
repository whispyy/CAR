package main;

import java.io.IOException;

import serveur.Serveur;

//Le main s'occupe de lancer le serveur et d'afficher les messages
//de bienvenue
public class Main {
	public static void main(String[] args) throws IOException{
		Serveur.accueilServeur();
		Serveur.initPort();
		Serveur.lancementServeur();
	}
}
