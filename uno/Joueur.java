package uno;

import java.util.ArrayList;
import java.util.HashMap;

import io.Clavier;

/**
 * cette classe représente le joueur : ce qu'il a et ce qu'il peut faire, çàd ses propriétés et ses actions
 * @author Stoufa
 *
 */
public class Joueur {

	/**
	 * la main du joueur contenant toutes ses cartes
	 */
	private Main main;
	/**
	 * le pseudo du joueur
	 */
	private String pseudo;
	/**
	 * la pioche : la pile des cartes où le joueur peut prendre des cartes dans le cas où il n'a pas de cartes jouables
	 */
	private Pioche pioche;
	/**
	 * le talon : la pile des cartes où les joueurs dépose leurs cartes
	 */
	private Talon talon;

	/**
	 * contructeur
	 * @param pseudo : pseudo du joueur
	 * @param pioche : la pioche
	 * @param talon : le talon
	 */
	public Joueur(String pseudo, Pioche pioche, Talon talon) {
		this.pseudo = pseudo;
		this.pioche = pioche;
		this.talon = talon;
		main = new Main();
	}
	
	/**
	 * permet de prender une carte de la pioche et l'ajouter à la main du joueur
	 * @return la carte tirée
	 */
	public Carte prendreCarte() {
		if (pioche.nbCartes() == 0) {	// la pioche est vide !
			System.out.println("La pioche est vide !");
			// dans ce cas, on doit utiliser le talon ( sauf le sommet ) pour la populer de nouveau
			Carte sommetTalon = talon.depiler();
			while (talon.nbCartes() != 0) {	// cette boucle va vider le talon dans la pioche
				pioche.empiler(talon.depiler());
			}
			pioche.melanger();
			talon.empiler(sommetTalon);	// remettre le sommet du talon
		}
		Carte carte = pioche.depiler();
		main.ajouter(carte);
		return carte;
	}
	
	/**
	 * cette méthode permet d'afficher les cartes dans la main du joueur courant
	 */
	public void afficherMain() {
		String str = "";
		ArrayList<Carte> cartes = main.getCartes();
		if (cartes.isEmpty()) {
			str = "[VIDE]";
		}
		for(int i = 0; i < cartes.size(); ++i) {
			Carte carte = cartes.get(i);
			str = str + i + ") " + carte.toString();
			if (carte.compatible(talon.sommet())) {
				str = str + " [Jouable]";
			}
			if (i != cartes.size() - 1) {	// if this isn't the last iteration
				str = str + "\n";	// add a new line
			}
		}
		System.out.println(str);
	}
	
	/**
	 * permet au joueur de jouer son tour
	 */
	public void jouerTour() {	// TODO : ajouter le cas où on a des doublons ! on doit se débarasser de toutes les occurences de la carte jouée !
		talon.afficherSommet();
		afficherMain();
		if (nbCartesJouables() == 0) {
			System.out.println("Vous n'avez pas de cartes jouables ! vous devez piocher !");
			pause();
			Carte c = prendreCarte();
			System.out.println("La carte piochée est : " + c);
			if (!c.compatible(talon.sommet())) {	// la carte récemment piochée n'est pas compatible avec le sommet du talon
				System.out.println("Pas de chance ! vous n'avez encore pas de cartes jouables, vous devez passer le tour");
				System.out.println("----------------------------------");
				return;	// passer le tour <=> quitter la méthode
			} else {
				afficherMain();
			}
		}
		jouerCarte();
		System.out.println("----------------------------------");
	}
	
	/**
	 * permet d'attendre un peu pour que l'utilisateur arrive à lire le message affiché
	 */
	private void pause() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * permet au joueur de jouer une carte
	 */
	private void jouerCarte() {
		boolean carteJouable = false;
		int num = -1;
		ArrayList<Carte> cartes = main.getCartes();
		
		System.out.println("Vous avez " + nbCartesJouables() + " cartes jouables");
		while (!carteJouable) {	// tant que la carte n'est pas jouable
			num = Clavier.lireEntier(cartes);
			Carte carteAjouer = cartes.get(num);
			if (carteAjouer.compatible(talon.sommet())) {
				carteJouable = true;
			} else {
				System.out.println(carteAjouer + " ne peut pas être jouée sur " + talon.sommet());
			}
		}
		
		Carte carte = main.retirer(num);
		if (carte.getCouleur() == Couleur.NOIR) {	// TODO : ce test doit être déléguée à la classe Jeu
			// On doit demander une couleur au joueur
			System.out.println("Vous devez choisir une couleur");
			Couleur couleur = donnerCouleur();
			// Si elle est de couleur noir, on est sûr qu'elle est spéciale !
			((CarteSpecial) carte).setCouleur(couleur);
		}
		talon.empiler(carte);
		System.out.println(pseudo + " a joué " + carte);
	}

	/*
	public void jouerTour() {
		Scanner scanner = new Scanner(System.in);
		boolean carteJouable = false;
		boolean horsBornes;
		int num = -1;
		ArrayList<Carte> cartes = main.getCartes();
		
		System.out.println("Vous avez " + this.nbCartesJouables() + " cartes jouables");
		
		while (!carteJouable) {	// tant que la carte n'est pas jouable
			do {
				System.out.print("? >> ");
				num = scanner.nextInt();
				horsBornes = num < 0 || num >= cartes.size();
				if (horsBornes) {
					System.out.println("indice hors bornes ! [0.." + (cartes.size() - 1) + "]");
				}
			} while (horsBornes);
			Carte carteAjouer = cartes.get(num);
			if (carteAjouer.compatible(talon.sommet())) {
				carteJouable = true;
			} else {
				System.out.println(carteAjouer + " ne peut pas être jouée sur " + talon.sommet());
			}
		}
		
		scanner.close();
		Carte carte = main.retirer(num);
		talon.empiler(carte);
		System.out.println(nom + " a joué " + carte);
	}
	*/
	
	/**
	 * permet au joueur de donner une couleur
	 * @return la couleur choisie
	 */
	private Couleur donnerCouleur() {
		HashMap<Integer, Couleur> menu = new HashMap<>();
		int i = -1;
		for(Couleur couleur : Couleur.values()) {
			if (couleur != Couleur.NOIR) {	// la couleur doit être différente de NOIR
				i++;
				menu.put(i, couleur);
				System.out.println(i + ") " + couleur.getValeur());
			}
		}
		int choix = Clavier.lireEntier(0, i);	// le choix doit être entre 0 et i
		return menu.get(choix);
	}

	/**
	 * cette méthode doit être privé ! seul le joueur doit connaître combien il a de cartes jouables !
	 * @return le nombre de cartes jouables çàd : compatibles avec le sommet du talon 
	 */
	private int nbCartesJouables() {
		int n = 0;
		ArrayList<Carte> cartes = main.getCartes();
		if (cartes.isEmpty()) {
			return 0;
		}
		Carte sommetTalon = talon.sommet();
		for(int i = 0; i < cartes.size(); ++i) {
			Carte carte = cartes.get(i);
			if (carte.compatible(sommetTalon)) {
				n++;
			}
		}
		return n;
	}
	
	/**
	 * contrairement à nbCartesJouables(), cette fonction doit être publique
	 * les autres joueurs peuvent voir combien vous avez de cartes dans la main
	 * @return le nombre de cartes que posséde le joueur dans sa main
	 */
	public int nbCartes() {
		return main.nbCartes();
	}

	/**
	 * @return le pseudo du joueur courant
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * @return chaîne décrivant le joueur en cours
	 * le joueur est identifié par son pseudo
	 */
	public String toString() {
		return getPseudo();
	}
	
	
}
