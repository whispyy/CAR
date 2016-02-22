package main;

import java.io.IOException;

import serveur.Serveur;


public class Main {
	public static void main(String[] args) throws IOException{
		Serveur.accueilServeur();
		Serveur.initPort();
		Serveur.lancementServeur();
	}
}
