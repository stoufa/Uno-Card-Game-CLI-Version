package uno;

/**
 * cette classe représente les cartes chiffres du jeu : 0..9 x BLEU,JAUNE,VERT,ROUGE
 * @author Stoufa
 *
 */
public class CarteChiffre extends Carte {

	/**
	 * la valeur de la carte, doit être un entier entre 0 et 9
	 */
	private int valeur;
	
	/**
	 * constructeur
	 * @param couleur : la couleur de la carte
	 * @param valeur : la valeur de la carte
	 */
	public CarteChiffre(Couleur couleur, int valeur) {
		super(couleur);
		this.valeur = valeur;
	}
	
	/**
	 * permet de retourner une chaîne contenant la représentation de l'objet courant
	 */
	@Override
	public String toString() {
		return super.toString() + " " + valeur;
	}

	/**
	 * @return true : si la carte courante et celle passée en paramétre sont compatibles ( çàd jouable )
	 * @return false : sinon
	 */
	@Override
	public boolean compatible(Carte carte) {
		if (carte instanceof CarteChiffre) {	//	CarteChiffre
			// même couleur ou même valeur ?
			return ( carte.couleur == couleur ) || ( ((CarteChiffre) carte).valeur == valeur );	// même couleur ou même valeur
		} else {	// CarteSpecial
			// même couleur ?
			return carte.couleur == couleur;	// même couleur
		}
	}

}
