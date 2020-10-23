package ca.cegepvicto.techinfo.a2019.p3.da1737508.algogenetique;

import java.util.Random;
/**
 * Étienne Sévégny (1737508)
 * Algo
 * Crée un algorithme intelligent de gène qui ne doivent pas toucher à des gènes de même couleur.
 */
public class Algo {
    private Node[][] tableauDeGene;
    private int qteDeGene;
    private int qteDeNode;
    private Node[] geneGagnant = null;
    private int[] noteGene = null;

    /**
     * Algo
     * Constructeur de l'algorithme génétique
     * @param qteDeGene La quantité de gènes à créer et à tester.
     * @param qteDeNode La quantité de noeuds par gène.
     */
    public Algo(int qteDeGene, int qteDeNode) {
        if (qteDeGene <= 0) {
            throw new IllegalArgumentException("Tu ne peux pas entrer une quantitée négative.");
        }
        if (qteDeNode <= 0) {
            throw new IllegalArgumentException("Tu ne peux pas entrer une quantitée négative.");
        }
        if (qteDeGene > 100) {
            throw new IllegalArgumentException("Tu ne peux pas entrer une valeur en haut de 100.");
        }
        if (qteDeNode > 100) {
            throw new IllegalArgumentException("Tu ne peux pas entrer une valeur en haut de 100.");
        }
        this.qteDeNode = qteDeNode;
        this.qteDeGene = qteDeGene;
        this.tableauDeGene = new Node[qteDeGene][];
        CreerGene();
    }

    /**
     * EstCorrect
     * Vérificateur et donneur de notes
     */
    private void EstCorrect() {
        noteGene = new int[tableauDeGene.length];
        for (int k = 0; k < tableauDeGene.length; k++) {
            noteGene[k] = 0;
            for (int i = 0; i < tableauDeGene[k].length; i++) {
                Node[] verification = tableauDeGene[k][i].getConnect();
                for (int j = 0; j < tableauDeGene[k][i].getNbDeConnection(); j++) {
                    if (verification[j].getCouleur() != tableauDeGene[k][i].getCouleur()) {
                        noteGene[k] = noteGene[k] + 1;
                    }
                }
            }
        }
    }

    /**
     * CreeGene
     * Crée des gènes pour le tableau au début.
     */
    private void CreerGene() {
        for (int i = 0; i < tableauDeGene.length; i++) {
            tableauDeGene[i] = new Node[qteDeNode];
            for (int j = 0; j < tableauDeGene[i].length; j++) {
                tableauDeGene[i][j] = new Node();
            }
            int nbConnexion = (qteDeNode) / 2;
            for (int j = 0; j < tableauDeGene[i].length; ) {
                int nouvelleConnexionUn = 0;
                int nouvelleConnexionDeux = 0;
                while (nouvelleConnexionUn == nouvelleConnexionDeux) {
                    Random rand = new Random();
                    nouvelleConnexionUn = rand.nextInt(qteDeNode);
                    Random randDeux = new Random();
                    nouvelleConnexionDeux = randDeux.nextInt(qteDeNode);
                }
                boolean nEstPasCreer = true;
                for (int k = 0; k < tableauDeGene[i][nouvelleConnexionUn].getConnect().length; k++) {
                    if (tableauDeGene[i][nouvelleConnexionUn].getConnect()[k] == tableauDeGene[i][nouvelleConnexionDeux]) {
                        nEstPasCreer = false;
                    }
                }
                if (nEstPasCreer == true) {
                    tableauDeGene[i][nouvelleConnexionUn].AjouterConnexion(tableauDeGene[i][nouvelleConnexionDeux]);
                    tableauDeGene[i][nouvelleConnexionDeux].AjouterConnexion(tableauDeGene[i][nouvelleConnexionUn]);
                    j++;
                }
            }
        }
    }

    ;

    /**
     * Mutation
     * Mute 5% de tout les gènes.
     */
    private void Mutation() {
        noteGene = new int[tableauDeGene.length];
        for (int i = 0; i < tableauDeGene.length; i++) {
            for (int j = 0; j < tableauDeGene[i].length; j++) {
                Random rand = new Random();
                int change = rand.nextInt(100);
                if (change <= 20) {
                    tableauDeGene[i][j].ChangerCouleur();
                }
            }
        }
    }

    /**
     * Tournoi
     * Garde le tier du tableau de gènes avec les meilleurs notes et remplace les autres avec une nouvelle génération.
     */
    private void Tournoi() {
        EstCorrect();
        for (int i = 0; i < noteGene.length; i++) {
            int plusGrand = i;
            for (int j = i; j < noteGene.length; j++) {
                if (noteGene[j] > noteGene[plusGrand]) {
                    plusGrand = j;
                }
            }
            Node[] swap = tableauDeGene[plusGrand];
            tableauDeGene[plusGrand] = tableauDeGene[i];
            tableauDeGene[i] = swap;
            int swapNb = noteGene[plusGrand];
            noteGene[plusGrand] = noteGene[i];
            noteGene[i] = swapNb;
        }
        Node[][] nouveauTableau = new Node[qteDeGene][];
        for (int i = 0; i < tableauDeGene.length / 3; i++) {
            nouveauTableau[i] = tableauDeGene[i];
        }
        for (int i = tableauDeGene.length / 3; i < tableauDeGene.length; i++) {
            nouveauTableau[i] = new Node[qteDeNode];
            for (int j = 0; j < nouveauTableau[i].length; j++) {
                nouveauTableau[i][j] = new Node();
            }
            for (int j = 0; j < nouveauTableau[i].length; ) {
                int nouvelleConnexionUn = 0;
                int nouvelleConnexionDeux = 0;
                while (nouvelleConnexionUn == nouvelleConnexionDeux) {
                    Random rand = new Random();
                    nouvelleConnexionUn = rand.nextInt(qteDeNode);
                    Random randDeux = new Random();
                    nouvelleConnexionDeux = randDeux.nextInt(qteDeNode);
                }
                boolean nEstPasCreer = true;
                for (int k = 0; k < nouveauTableau[i][nouvelleConnexionUn].getConnect().length; k++) {
                    if (nouveauTableau[i][nouvelleConnexionUn].getConnect()[k] == nouveauTableau[i][nouvelleConnexionDeux]) {
                        nEstPasCreer = false;
                    }
                }
                if (nEstPasCreer == true) {
                    nouveauTableau[i][nouvelleConnexionUn].AjouterConnexion(nouveauTableau[i][nouvelleConnexionDeux]);
                    nouveauTableau[i][nouvelleConnexionDeux].AjouterConnexion(nouveauTableau[i][nouvelleConnexionUn]);
                    j++;
                }
            }
        }
        tableauDeGene = nouveauTableau;
    }
    /**
     * Run
     * Part l'algoritme génétique.
     * @return Le tableau gagnant
     */
    public Node[] Run() {
        boolean estCorrect = false;
        while (true) {
            Tournoi();
            for (int i = 0; i < tableauDeGene.length; i++) {
                if (noteGene[i] == qteDeNode * 2) {
                    geneGagnant = tableauDeGene[i];
                    return geneGagnant;
                }
            }
            Mutation();
        }
    }
}
