package io;

import java.util.ArrayList;
import java.util.Scanner;

import uno.Carte;

/**
 * cette classe nous permet d'interagir avec le joueur à travers le clavier
 * @author Stoufa
 *
 */
public class Clavier {

    /**
     * grace à cet objet on peut lire à partir du clavier
     */
    private static Scanner scanner = new Scanner( System.in );

    /**
     * permet de lire un entier à partir du clavier
     * @return l'entier lu
     */
    public static int lireEntier() {
        System.out.print( "? >> " );
        int res = scanner.nextInt();
        scanner.nextLine(); // pour consommer le caractére \n à la fin de la chaîne
        // sinon, on va avoir des problèmes si on souhaite ensuite lire une chaîne de caractères !
        // http://stackoverflow.com/questions/13102045/scanner-is-skipping-nextline-after-using-next-nextint-or-other-nextfoo
        return res;
    }

    /**
     * permet de lire un entier entre min et max
     * @param min	la borne inférieure de l'entier à lire
     * @param max	la borne supérieure de l'entier à lire
     * @return		l'entier qu'on veut lire du clavier
     */
    public static int lireEntier( int min, int max ) {
        if ( min > max ) {
            // inverser les bornes
            int temp = min;
            min = max;
            max = temp;
        }
        boolean horsBornes;
        int num;
        do {
            num = lireEntier();
            horsBornes = num < min || num > max;
            if ( horsBornes ) {
                System.out.println( "indice hors bornes ! [" + min + ".." + max + "]" );
            }
        } while ( horsBornes );
        return num;
    }

    /**
     * permet de lire un entier qui représente l'une des cases de la liste arrayList
     * @param arrayList la liste dont on veut lire un indice valide
     * @return l'indice de l'une des cases de la liste arrayList
     */
    public static int lireEntier( ArrayList<Carte> arrayList ) {
        if ( arrayList.isEmpty() ) {
            return -1;
        }
        return lireEntier( 0, arrayList.size() - 1 ); // les listes sont indéxés de 0 à nbElements - 1
    }

    /**
     * permet de lire une chaîne de caractères à partir du clavier
     * @return la chaîne lue
     */
    public static String lireChaine() {
        System.out.print( "? >> " );
        return scanner.nextLine();
    }

    /**
     * permer de fermer la variable scanner à la fin du programme
     */
    public static void fermer() {
        scanner.close();
    }

}
