/* 
 * File:   Labo9.Java
 * POO1 - Labo9
 * Author: Gandalf le blanc
 * Modified by: Alleman Adrien le sage, Smith James le brave
 *
 * Created on 14. decembre 2018
 */
package labo9_seigneursanneaux;

import java.util.LinkedList;

/** Lieu **/
enum Lieu {
    Comte("La Comte"),
    Destin("Le Mont du Destin");

    private final String lieu;

    private Lieu(String nomLieu) {
        lieu = nomLieu;
    }

    public String toString() {
        return lieu;
    }
}

/** Personne **/
class Personne {

    protected String nom;
    private Lieu lieu;
    private Anneau anneau;

    static private LinkedList alives = new LinkedList();

    public Personne(String nom, Lieu lieu) {
        this.lieu = lieu;
        this.nom = nom;
        alives.add(this);
    }

    public void deplacer(Lieu destination) {
        lieu = destination;
    }

    public void mourir() {
        System.out.printf("%s meurt!\n", nom);
        alives.remove(this);
    }

    static public void afficherPersonnes() {
        for (Object o : alives) {
            Personne p = (Personne) o;

            System.out.println(p);
        }
    }

    public boolean recevoirAnneau(Anneau a) {
        if (anneau == null) {

            anneau = a;
            return true;

        } else {
            return false;
        }
    }

    public boolean perdreAnneau() {
        if (anneau != null) {

            anneau = null;
            return true;

        } else {
            return false;
        }
    }

    public String toString() {
        return nom;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void finalize() {
        System.out.println(nom + " meurt paisiblement!");
    }
}

/** Ennemi **/
class Ennemi extends Personne {

    private static Anneau anneauUnique;
    Ennemi(String nom, Lieu lieu) {
        super(nom, lieu);
    }

    Anneau anneau() {
        if(anneauUnique == null)
            anneauUnique = new AnneauPouvoir(this, "de Pouvoir");
        
        return anneauUnique;
    }

    @Override
    public void finalize() {
        System.out.println(nom + " rejoint le grand vide cosmique.");
    }
    
    /** AnneauUnique / AnneauPouvoir **/
    class AnneauPouvoir extends Anneau {

        Lieu lieuCreation;

        AnneauPouvoir(Personne createur, String nom) {
            
            super(createur, nom);
            lieuCreation = createur.getLieu();
        }

        @Override
        public void utiliser() {
            if (proprietaire == createur) {
                System.out.println(proprietaire + " est tout-puissant!");
            } else {
                System.out.println(proprietaire + " devient invisible!");
            }
        }

        public void detruire() {
            System.out.println(proprietaire.getLieu() + ": " + proprietaire + " tente de détruire l'anneau " + nom + "...");
            if (proprietaire.getLieu() == lieuCreation) {
                super.detruire();
                createur.mourir();
            } else {
                System.out.println("L'anneau " + nom + " ne peut être détruit que là où il a été créé.");
            }
        }
    }
}

/** Anneau **/
abstract class Anneau {

    protected String nom;
    protected Personne proprietaire;
    protected final Personne createur;

    Anneau(Personne createur, String nom) {
        this.nom = nom;
        proprietaire = createur;
        this.createur = createur;

        System.out.println(createur.getLieu() + ": création de l'anneau " + nom + " par " + createur + "!");

        definirProprietaire(createur);
    }

    public void definirProprietaire(Personne p) {

        if (p.recevoirAnneau(this)) {
            proprietaire = p;
            System.out.println(p + " possède l'anneau " + this.nom + ".");
        } else {
            System.out.println(p + " a déjà un anneau !");
        }
    }

    public void detruire() {
        System.out.printf("L'anneau %s est détruit.\n", nom);
        if (proprietaire != null) {
            proprietaire.perdreAnneau();
        }
    }

    public abstract void utiliser();
}

class Labo9 {

    public Labo9() {
        Personne frodo = new Personne("Frodo", Lieu.Comte);
        Ennemi sauron = new Ennemi("Sauron", Lieu.Destin);

        System.out.println("-1-");
        Anneau anneau = sauron.anneau();

        System.out.println("-2-");
        anneau.utiliser();

        System.out.println("-3-");
        anneau.definirProprietaire(frodo);

        System.out.println("-4-");
        anneau.utiliser();

        System.out.println("-5-");
        anneau.detruire();

        System.out.println("-6-");
        frodo.deplacer(Lieu.Destin);
        anneau.detruire();

        System.out.println("-7-");

    }

    public static void main(String... args) {
        new Labo9();
        System.gc();
    }
}
