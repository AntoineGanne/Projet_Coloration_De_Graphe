package Modele;

import java.util.ArrayList;
import java.util.Comparator;


public class Sommet implements Comparable<Sommet> {
    ArrayList<Sommet> successeurs;



    int idCouleur;
    static final int DEFAULT_COLOR=-1;

    int id; //correspond a l'emplacement du sommet dans l'arraylist du graphe

    String nom;
    static final String DEFAULT_NAME="Anonyme";

    Sommet(String nomInput,int idInput){
        this.successeurs=new ArrayList<>(0);
        this.idCouleur=DEFAULT_COLOR;
        this.nom=nomInput;
        this.id=idInput;
    }

    Sommet(int idInput){
        this(DEFAULT_NAME,idInput);
    }

    void ajouterSuccesseur(Sommet nouveauSuccesseur){
        //on verifie que le successeur ne soit pas déjà présent
        if(!successeurs.contains(nouveauSuccesseur)){
            successeurs.add(nouveauSuccesseur);
        }
    }

    /**
     * renvoit vrai si l'un des successeur du sommet est de la couleur donnée en paramètre.
     * Problème possible dans le cas d'un graphe orienté?
     * @param couleur
     * @return renvoit vrai si l'un des successeur du sommet est de la couleur donnée en paramètre.
     */
    boolean estAdjacentACouleur(int couleur){
        for (Sommet x:
             this.getSuccesseurs()) {
            if(x.getIdCouleur()==couleur){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return nombre de couleurs différentes dans les sommets adjacents
     */
    int DsaturValue(){
        ArrayList<Integer> couleursAdjacentes=new ArrayList<>();
        for(Sommet s :successeurs){
            int couleurS=s.getIdCouleur();
            if(!s.haveDefaultColor()&&!couleursAdjacentes.contains(couleurS)){
                couleursAdjacentes.add(couleurS);
            }
        }
        return couleursAdjacentes.size();
    }

    public ArrayList<Sommet> getSuccesseurs() {
        return successeurs;
    }
    public int getNombreDeSuccesseurs(){return successeurs.size();}

    public int getIdCouleur() {
        return idCouleur;
    }
    public void setIdCouleur(int idCouleur) {
        this.idCouleur = idCouleur;
    }
    public void resetCouleur() {
        this.idCouleur = DEFAULT_COLOR;
    }
    public boolean haveDefaultColor(){return this.idCouleur==DEFAULT_COLOR;}

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    /**
     * operateur de comparaison entre deux sommet. La comparaison se fait sur le nombre de successeurs
     * @param s
     * @return
     */
    @Override
    public int compareTo(Sommet s) {
        int thisNbSuccesseurs=this.getNombreDeSuccesseurs();
        int autresSommetNbSuccesseur=s.getNombreDeSuccesseurs();

        if(thisNbSuccesseurs<autresSommetNbSuccesseur)return 1;
        else if(thisNbSuccesseurs==autresSommetNbSuccesseur) return 0;
        else return -1;
    }
}
