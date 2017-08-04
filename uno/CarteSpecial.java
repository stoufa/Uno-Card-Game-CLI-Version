package uno;

/**
 * cette classe représente les cartes spéciales du jeu
 * @author Stoufa
 *
 */
public class CarteSpecial extends Carte {

	/**
	 * symbole de la carte
	 * @see Symbole
	 */
	private Symbole symbole;
	
	/**
	 * constructeur
	 * @param couleur : la couleur de la carte
	 * @param symbole : le symbole de la carte
	 */
	public CarteSpecial(Couleur couleur, Symbole symbole) {
		super(couleur);
		this.symbole = symbole;
	}
	
	/**
	 * retourne une chaîne contenant la représentation de la carte 
	 */
	@Override
	public String toString() {
		return super.toString() + " " + symbole.getValeur();
	}
	
	/**
	 * @return true : si la carte courante est compatible avec celle passée en paramétre (çàd : jouable)
	 * @return false : sinon
	 * @param carte : la carte à comparer avec l'objet courant
	 */
	@Override
	public boolean compatible(Carte carte) {
		if (couleur == Couleur.NOIR) {
			return true;	// Les cartes noirs ( nottament le Joker et la carte +4 )
			// peuvent être déposée sur n'importe qu'elle autre carte
		}
		if (carte instanceof CarteSpecial) {	//	CarteSpecial
			// même couleur ou même symbole ?
			return ( carte.couleur == couleur ) || ( ((CarteSpecial) carte).symbole == symbole );	// même couleur ou même symbole
		} else {	// CarteChiffre
			// même couleur ?
			return carte.couleur == couleur;	// même couleur
		}
	}
	
	/**
	 * permet de changer la couleur de la carte spéciale et ceci n'est possible
	 * que si la couleur initiale de la carte est NOIR
	 * @param couleur : peut être ROUGE, JAUNE, VERT ou BLEU
	 */
	public void setCouleur(Couleur couleur) {
		if (this.couleur == Couleur.NOIR) {
			// On ne peut changer la couleur que quand la couleur initiale de
			// la carte est noire : Joker ou +4
			this.couleur = couleur;
		}
	}

	/**
	 * permet de retourner le symbole de la carte spéciale
	 * @return le symbole de la carte spéciale
	 */
	public Symbole getSymbole() {
		return symbole;
	}
	
	

}
