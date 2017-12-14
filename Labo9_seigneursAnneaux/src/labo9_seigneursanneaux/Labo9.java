class Personne
{
  private String nom;
  private Lieu lieu;

  public void mourir() {
    System.out.printf("%s meurt!\n", nom);
  }
}

abstract class Anneau
{
  private String nom;
  private Personne propriétaire;

  public void detruire() { 
    System.out.printf("L'anneau %s est détruit.\n", nom);
  }
}

class Labo9
{
  public LotR() 
  {
    Personne frodo = new Personne("Frodo", Lieu.Comte);
    Ennemi sauron  = new Ennemi("Sauron", Lieu.Destin);

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

  public static void main(String ... args)  {
    new LotR();
    System.gc();
  }
}
