package Modele;

import java.util.ArrayList;


public class Sommet {
    ArrayList<Sommet> successeurs;

    int idCouleur;
    static final int DEFAULT_COLOR=-1;

    String nom;
    static final String DEFAULT_NAME="Anonyme";

    Sommet(String nomInput){
        this.successeurs=new ArrayList<>(0);
        this.idCouleur=DEFAULT_COLOR;
        this.nom=nomInput;
    }

    Sommet(){
        this(DEFAULT_NAME);
    }
}
