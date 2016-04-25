package car.tp4;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Livre {
	private static int id_value = 0;
	
	
	@Id
	private int id;
	private String titre;
	private String auteur;
	private String annee;

	public Livre() {

	}

	public Livre(String titre, String auteur, String annee) {
		this. id = ++id_value;
		this.titre = titre;
		this.auteur = auteur;
		this.annee = annee;
	}

	public int getId() {
		return id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}
}
