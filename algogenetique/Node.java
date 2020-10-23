package ca.cegepvicto.techinfo.a2019.p3.da1737508.algogenetique;
import java.util.Random;

/**
 * Étienne Sévégny (1737508)
 * Node
 * Classe de noeud d'une certaine couleur connectée à d'autres noeuds.
 */
public class Node {
    private String couleur;
    private Node[] connect;
    private int nbDeConnection;

    /**
     * Node
     * Construit un nouveau noeud.
     */
    public Node() {
        ChangerCouleur();
        connect = new Node[10];
        nbDeConnection=0;
    }

    /**
     * AjouterConnexion
     * Ajoute une nouvelle connexion
     * @param nouvellenode le noeud à connecter
     */
    public void AjouterConnexion(Node nouvellenode){
        if(nouvellenode == null){
            throw new NullPointerException("La node est nulle");
        }
        if(connect.length > nbDeConnection){
            connect[nbDeConnection]=nouvellenode;
            nbDeConnection+=1;
        }
    }

    /**
     * ChangerCouleur
     * Change la couleur du noeud de manière random.
     */
    public void ChangerCouleur(){
        Random rand = new Random();
        int nouvelleCouleur = rand.nextInt(5);
        if(nouvelleCouleur == 0){
            couleur = "Rouge";
        }
        else if(nouvelleCouleur == 1){
            couleur = "Bleu";
        }
        else if(nouvelleCouleur == 2){
            couleur = "Vert";
        }
        else if(nouvelleCouleur == 3){
            couleur = "Magenta";
        }
        else if(nouvelleCouleur == 4){
            couleur = "Cyan";
        }

    }

    /**
     * getCouleur
     * Sort la couleur du noeud
     * @return String getCouleur
     */
    public String getCouleur() {
        return couleur;
    }
    /**
     * getConnect
     * retourne le tableau de connexion.
     * @return Node[] connect
     */
    public Node[] getConnect() {
        return connect;
    }

    /**
     * getNbDeConnection
     * Retourne le nombre de connection
     * @return int nbDeConnection
     */
    public int getNbDeConnection() {
        return nbDeConnection;
    }


}
