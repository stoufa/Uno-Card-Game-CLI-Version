package uno;

/**
 * cette classe regroupe les aspets communs de toutes les cartes
 * @author Stoufa
 *
 */
public abstract class Carte {

	/**
	 * la couleur de la carte
	 * @see Couleur
	 */
	protected Couleur couleur;
	
	/**
	 * constructeur
	 * @param couleur : la couleur de la carte
	 */
	public Carte(Couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * @param carte : la carte qu'on voit sur le sommet du talon
	 * @return true : Si la carte courante peut être déposée sur l'objet ( carte )
	 * @return false : sinon
	 * abstraite carte le méchanisme de comparaison différe entre la carte chiffre et la carte spéciale
	 */
	public abstract boolean compatible(Carte carte);
	
	/**
	 * retourne une chaîne descrivant l'objet courant
	 */
	public String toString() {
		return couleur.getValeur();
	}

	/**
	 * permet de retourner la couleur de la carte courante
	 * @return la couleur de la carte courante
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
}
