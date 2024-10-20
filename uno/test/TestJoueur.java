package uno.test;

import uno.Joueur;
import uno.Pioche;
import uno.Talon;

public class TestJoueur {

	public static void main(String[] args) {
		Pioche pioche = new Pioche();
		pioche.melanger();
		Talon talon = new Talon(pioche);
		
		int nbJoueurs = 2;
		//Joueur j1, j2;
		Joueur[] joueurs = new Joueur[nbJoueurs];
		
		for (int i = 1; i <= nbJoueurs; i++) {
			joueurs[i - 1] = new Joueur("j" + i, pioche, talon);
		}
		
		//j1 = new Joueur("j1", pioche, talon);
		//j2 = new Joueur("j2", pioche, talon);
		//System.out.println(pioche);
		for (int i = 0; i < joueurs.length; i++) {
			for (int j = 0; j < 7; j++) {	// Chaque joueur va prendre 7 cartes
				joueurs[i].prendreCarte();
			}
		}
		
		/*
		for(int i = 0; i < 7; i++) {
			j1.prendreCarte();
			j2.prendreCarte();
		}
		*/
		
		//talon.ajouterPremiereCarte(pioche);
		
		//System.out.println("==================");
		//System.out.println(pioche);
		//System.out.println("==================");
		//System.out.println("==================");
		//System.out.println("Main de j1 : ");
		//j1.afficherMain();
		//System.out.println("Main de j2 : ");
		//j2.afficherMain();
		//System.out.println("Talon");
		//talon.afficher();
		//talon.ajouter(new CarteChiffre(Couleur.NOIR, 100));	// test
		/*
		while (j1.nbCartes() != 0) {
			j1.jouerTour();
			j2.jouerTour();			
		}
		*/
		
		int direction = 1;	//	peut être soit 1 soit -1
		int indice = 0;	// indice du joueur qui a le tour de jouer
		Joueur joueurCourant;
		while (true) {
			joueurCourant = joueurs[indice];
			System.out.println("Tour de " + joueurCourant.getPseudo());
			joueurCourant.jouerTour();
			if (joueurCourant.nbCartes() == 0) {
				System.out.println(joueurCourant.getPseudo() + " a gagné !");
				break;
			}
			// avancer vers le joueur suivant
			indice += direction;
			if (indice < 0) {
				indice += nbJoueurs;
			}
			if (indice > nbJoueurs - 1) {	// indice >= nbJoueurs
				indice -= nbJoueurs;
			}
			System.out.println();
		}
		
		System.out.println("=== Fin de la partie ===");
		
		//System.out.println("Talon");
		//talon.afficher();
		
	}
	
}
