/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo9_seigneursanneaux;

/**
 *
 * @author James
 */
public class Labo9_seigneursAnneaux {

    Labo9_seigneursAnneaux(){
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
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Labo9_seigneursAnneaux();
        System.gc();
    }
    
}
