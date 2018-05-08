package Modele;

import java.util.ArrayList;


public class Sommet {
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

    public ArrayList<Sommet> getSuccesseurs() {
        return successeurs;
    }

    public int getIdCouleur() {
        return idCouleur;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
