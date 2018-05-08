package Modele;

import java.util.ArrayList;
import java.util.Scanner;

public class Graphe {
    ArrayList<Sommet> sommets;
    boolean estOriente;
    static final boolean DEFAULT_ETAT_ORIENTATION=true;

    String nomGraphe;
    static final String DEFAULT_GRAPHE_NAME="Graphe Anonyme";

    public Graphe(){
        sommets=new ArrayList<>(0);
        estOriente=DEFAULT_ETAT_ORIENTATION;
        nomGraphe=DEFAULT_GRAPHE_NAME;
    }

    public Graphe(boolean estOrienteInput){
        sommets=new ArrayList<>(0);
        estOriente=estOrienteInput;
    }

    public void ajouterSommet(String nomSommet){
        int index=sommets.size();
        Sommet nouveauSommet=new Sommet(nomSommet,index);
        sommets.add(nouveauSommet);
    }
    public void ajouterSommet(){

        int index=sommets.size();
        Sommet nouveauSommet=new Sommet(index);
        sommets.add(nouveauSommet);
    }

    public void ajouterArc(Sommet extremiteInitiale, Sommet extremiteFinale){
        extremiteInitiale.ajouterSuccesseur(extremiteFinale);
        //si le graphe n'est pas orienté, on ajoute aussi l'arc inverse
        if(!estOriente){
            extremiteFinale.ajouterSuccesseur(extremiteInitiale);
        }
    }

    public void ajouterArc(int indexSommetInitial,int indexSommetFinal) throws Exception {
        if(indexSommetInitial<0 || indexSommetInitial>sommets.size()
                || indexSommetFinal<0 || indexSommetFinal>sommets.size()){
            throw new Exception("Index d'ajout des arcs impossible");

        }
        ajouterArc(sommets.get(indexSommetInitial),sommets.get(indexSommetFinal));
    }



    public void setEstOriente(boolean estOriente) {
        this.estOriente = estOriente;
    }
    public String getNom() {
        return nomGraphe;
    }
    public void setNom(String nomGraphe) {
        this.nomGraphe = nomGraphe;
    }

}
