package uno;

/**
 * classe représentant la pioche du jeu
 * @author Stoufa
 *
 */
public class Pioche extends Pile {
	// TODO : on doit traiter le cas où la pioche devient vide et on doit prendre une carte
	// dans ce cas, on a besoin de mélanger le talon ( sauf le sommet ) et le déposer dans la pioche

	/**
	 * constructeur : permet de construire la pioche et d'y insérer toutes les cartes nécéssaires
	 */
	public Pioche() {
		for(Couleur couleur : Couleur.values()) {
			if (couleur == Couleur.NOIR) {
				for(int i = 0; i < 4; i++) {	// la pioche 4 cartes joker et 4 cartes +4
					ajouter(new CarteSpecial(Couleur.NOIR, Symbole.JOKER));
					ajouter(new CarteSpecial(Couleur.NOIR, Symbole.PLUS4));
				}
				continue;	// toutes les cartes noirs sont ajoutées, on passe à la couleur suivante
			}
			// 1 Carte 0 pour chaque couleur
			ajouter(new CarteChiffre(couleur, 0));
			// 2 Cartes 1..9 pour chaque couleur
			for(int i = 1; i <= 9; i++) {
				ajouter(new CarteChiffre(couleur, i));
				ajouter(new CarteChiffre(couleur, i));
			}
			// 2 Cartes passer, inverser, +2 pour chaque couleur
			for (int i = 0; i < 2; i++) {
				ajouter(new CarteSpecial(couleur, Symbole.PASSER));
				ajouter(new CarteSpecial(couleur, Symbole.INVERSER));
				ajouter(new CarteSpecial(couleur, Symbole.PLUS2));
			}
		}
	}

	/**
	 * Cette méthode est utilisée pour retourner une carte aléatoirement dans la pioche
	 * dans le cas où la premiére carte est une carte spéciale ( au début du jeu )
	 * @param carte
	 */
	private void retournerCarte(Carte carte) {
		int i = rand.nextInt(cartes.size());	// Entier aléatoire entre 0 et cartes.size() - 1
		cartes.add(i, carte);
	}
	
	/**
	 * cette méthode est appelée par le talon pour qu'elle lui retourne sa premiére carte
	 * la carte ne doit pas être spéciale
	 * @return
	 */
	public Carte premiereCarteTalon() {
		Carte carte;
		while (true) {
			carte = depiler();	// Retirer une carte
			if (carte instanceof CarteSpecial) {	// C'est une carte spéciale
				// Il faut dans ce cas la rajouter aléatoirement dans la pioche
				retournerCarte(carte);
				//System.out.println("Oops carte spécial , ...");
				//System.out.println(carte);
			} else {
				return carte;
			}
		}
	}


}
