package Modele;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Graphe {
    ArrayList<Sommet> sommets;
    boolean estOriente;
    static final boolean DEFAULT_ETAT_ORIENTATION=true;

    String nomGraphe;
    static final String DEFAULT_GRAPHE_NAME="Graphe Anonyme";

    static final int MAX_ID_COULEUR=2048;

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


    public void resetCouleurSommets(){
        for(Sommet s:
                sommets){
            s.resetCouleur();
        }
    }

    /////algorithmes de coloration
    public void greedyColoring() throws Exception {
        int c;
        resetCouleurSommets();
        ArrayList<Sommet> L=new ArrayList<>(sommets);
        L.sort(Sommet::compareTo);; //trie les sommets selon leur degré
        while (!L.isEmpty()){
            Sommet x=L.get(0);
            c=0;
            ArrayList<Integer> idCouleursSuccesseurs=new ArrayList<Integer>();
            for(Sommet succ :x.getSuccesseurs()){
                idCouleursSuccesseurs.add(succ.getIdCouleur());
            }
            while(idCouleursSuccesseurs.contains(c)){
                c++;
                if(c>MAX_ID_COULEUR) throw new Exception("Nombre de couleurs trop important");
            }
            x.setIdCouleur(c);
            L.remove(0);
        }
    }

    /**
     * teste si la coloration du graphe est correcte (2 sommets adjacents n'ont pas la même couleur)
     * @return vrai si la coloration est correcte;
     */
    public boolean ColorationEstCorrecte(){
        for(Sommet x :sommets){
            int idCouleurX=x.getIdCouleur();
            for(Sommet succ:x.getSuccesseurs()){
                int idCouleurSucc=succ.getIdCouleur();
                if(idCouleurSucc==idCouleurX)
                    return false;
            }
        }
        return true;
    }


    /////

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
