package Modele;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Graphe {
    ArrayList<Sommet> sommets;
    boolean estOriente;
    static final boolean DEFAULT_ETAT_ORIENTATION=true;

    String nomGraphe;
    static final String DEFAULT_GRAPHE_NAME="Graphe Anonyme";


    static final int MIN_ID_COULEUR=1;  //!! doit ètre superieur a DEFAULT_COLOR defini dans Sommet
    static final int MAX_ID_COULEUR=4000;


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

    /**
     * crée une arete
     * @param indexSommetInitial
     * @param indexSommetFinal
     * @throws Exception
     */
    public void ajouterArc(int indexSommetInitial,int indexSommetFinal) throws Exception {
        if(indexSommetInitial<0 || indexSommetInitial>sommets.size()
                || indexSommetFinal<0 || indexSommetFinal>sommets.size()){
            throw new Exception("Index d'ajout des arcs impossible");

        }

        ajouterArc(sommets.get(indexSommetInitial),sommets.get(indexSommetFinal));
    }


    public void ajouterArcAleatoire(){
        int nbSommets=sommets.size();
        boolean succesCreationArete=false;
        do{  // on selectionne aléatoirement 2 sommets qui ne sont pas voisin et on les relie
            int indexSommetInitial= (int) Math.floor(Math.random()*nbSommets);
            int indexSommetFinal;
            do{  // on ne veut pas d'aretes d'un sommet vers lui-même
                indexSommetFinal=(int) Math.floor(Math.random()*nbSommets);
            }while(indexSommetFinal==indexSommetInitial);
            if(!areteExisteEntreSommets(indexSommetInitial,indexSommetFinal)){
                try {
                   ajouterArc(indexSommetInitial,indexSommetFinal);
                    if(!estOriente){ //si il n'est pas orienté, on ajoute l'arete en sens inverse
                        ajouterArc(indexSommetFinal,indexSommetInitial);
                    }

                    succesCreationArete=true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }while (!succesCreationArete);
    }


    public void resetCouleurSommets(){
        for(Sommet s:
                sommets){
            s.resetCouleur();
        }
    }

    public boolean areteExisteEntreSommets(int indexSommetInitial,int indexSommetFinal){
        Sommet sInitial=sommets.get(indexSommetInitial);
        Sommet sFinal=sommets.get(indexSommetFinal);
        if(this.estOriente){
            return sInitial.estVoisinA(sFinal);
        }else{
            return sInitial.estVoisinA(sFinal) || sFinal.estVoisinA(sInitial);
        }
    }

    /////algorithmes de coloration

    /**
     * entrée principale pour lacoloration de graphe
     * @param modeColoration  int correspond a un algorithme de coloration
     * @param writeOnConsole si vrai, écrit des informations en console
     * @return
     */
    public double coloration(int modeColoration, boolean writeOnConsole) throws Exception {
        resetCouleurSommets();
        double start=0, end=0;
        String nomMethode="erreur nom methode";
        switch (modeColoration){
            case 1:
                nomMethode="Greedy";
                start = System.nanoTime();
                greedyColoring();
                end = System.nanoTime();
                if(!ColorationEstCorrecte()){
                    throw new Exception ("Coloration incorrecte!");
                }
                break;
            case 2:
                nomMethode="WelshPowell";
                start = System.nanoTime();
                WelshPowell();
                end = System.nanoTime();
                if(!ColorationEstCorrecte()){
                    throw new Exception ("Coloration incorrecte!");
                }
                break;
            case 3:
                nomMethode="Dsatur";
                start = System.nanoTime();
                DSATUR();
                end = System.nanoTime();
                if(!ColorationEstCorrecte()){
                    throw new Exception ("Coloration incorrecte!");
                }
                break;
        }


        if(writeOnConsole)System.out.println("Nombre de couleurs utilisées: "+nombreDeCouleursUtilisees());
        double tempsExec=(end-start)/1000000;
        if(writeOnConsole)System.out.println("temps d'execution avec "+nomMethode+" : "+tempsExec+" ms");
        return tempsExec;
    }

    public void testerToutesLesColorations(){
        double start=0, end=0;

        try {
            resetCouleurSommets();
            start = System.nanoTime();
            greedyColoring();
            end = System.nanoTime();
            double tempsExec=(end-start)/1000000;
            System.out.println("Algorithme greedy: \n"+"     nombre de couleurs: "+nombreDeCouleursUtilisees()+" \n"
                    +"      temps d'execution: "+tempsExec+" ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            resetCouleurSommets();
            start = System.nanoTime();
            WelshPowell();
            end = System.nanoTime();
            double tempsExec=(end-start)/1000000;
            System.out.println("Algorithme WelshPowell: \n"+"     nombre de couleurs: "+nombreDeCouleursUtilisees()+" \n"
                    +"      temps d'execution: "+tempsExec+" ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            resetCouleurSommets();
            start = System.nanoTime();
            DSATUR();
            end = System.nanoTime();
            double tempsExec=(end-start)/1000000;
            System.out.println("Algorithme DSATUR: \n"+"     nombre de couleurs: "+nombreDeCouleursUtilisees()+" \n"
                    +"      temps d'execution: "+tempsExec+" ms");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void greedyColoring() throws Exception {
        int c;
        ArrayList<Sommet> L=new ArrayList<>(sommets); //liste des sommets du graphe
        L.sort(Sommet::compareTo); //trie les sommets selon leur degré (complexité??)
        while (!L.isEmpty()){
            Sommet x=L.get(0);
            c=MIN_ID_COULEUR;
            //on recupère les couleurs utilisées dans les successeurs
            ArrayList<Integer> idCouleursSuccesseurs=new ArrayList<Integer>();
            for(Sommet succ :x.getSuccesseurs()){
                idCouleursSuccesseurs.add(succ.getIdCouleur());
            }
            //on prend la premiere couleur qui ne se trouve dans aucun successeur
            while(idCouleursSuccesseurs.contains(c)){
                c++;
                if(c>MAX_ID_COULEUR) throw new Exception("Nombre de couleurs trop important");
            }
            x.setIdCouleur(c);
            L.remove(0);
        }
    }

    public void WelshPowell() {
        ArrayList<Sommet> L=new ArrayList<Sommet>(sommets); //liste des sommets du graphe
        L.sort(Sommet::compareTo); //trie les sommets selon leur degré (complexité??)
        int k=MIN_ID_COULEUR;
        while(!L.isEmpty()){
            Sommet x=L.get(0);
            x.setIdCouleur(k);
            L.remove(0);//on retire x de L
            int i=0;
            while (i<L.size()) {
                Sommet y=L.get(i);
                if(!y.estAdjacentACouleur(k)){
                    y.setIdCouleur(k);
                    L.remove(i);
                }else{
                    i++;
                }
            }
            k++;
        }
    }

    public void DSATUR() {
        ArrayList<Sommet> L=new ArrayList<>(sommets); //liste des sommets du graphe
        //Ordonner les sommets par ordre décroissant de degrés
        L.sort(Sommet::compareTo); //trie les sommets selon leur degré . O(n*log(n))
        int k;
        //On colore un sommet de degré maximum avec la couleur 0.
        Sommet sInitial=L.get(0); //complexité en O(n)
        sInitial.setIdCouleur(MIN_ID_COULEUR);
        sInitial.miseAJourValeurDSATURDesSommetsAdjacents();
        L.remove(0);

        while(!L.isEmpty()){
            Sommet x=getSommetAvecPlusHauteDsaturValue(L);
            /////Colorer ce sommet avec la plus petite couleur possible
            //on recupère les couleurs utilisées dans les successeurs
            ArrayList<Integer> idCouleursSuccesseurs=new ArrayList<>();
            for(Sommet succ :x.getSuccesseurs()){
                idCouleursSuccesseurs.add(succ.getIdCouleur());
            }
            //on prend la premiere couleur qui ne se trouve dans aucun successeur
            k=MIN_ID_COULEUR;
            while(idCouleursSuccesseurs.contains(k)){
                k++;
            }
            x.setIdCouleur(k);
            x.miseAJourValeurDSATURDesSommetsAdjacents();
            L.remove(x);//on retire x de L
        }
    }

    /**
     * Choisir un sommet avec DSAT maximum. En cas d'égalité, choisir un sommet de degré maximal
     * (ssi L est triée selon les degrés decroissant)
     * @param L
     * @return s un sommet avec DSAT maximum
     */
    static Sommet getSommetAvecPlusHauteDsaturValue(ArrayList<Sommet> L){
        Sommet resultat=L.get(0);
        int bestDsaturValue=resultat.DsaturValue();
        Sommet s;
        for(int i=1;i<L.size();i++){
            s=L.get(i);
            if(s.DsaturValue()>bestDsaturValue){
                resultat=s;
                bestDsaturValue=resultat.DsaturValue();
            }
        }
        return resultat;
    }



    /**
     * teste si la coloration du graphe est correcte (2 sommets adjacents n'ont pas la même couleur)
     * @return vrai si la coloration est correcte;
     */
    public boolean ColorationEstCorrecte(){
        for(Sommet x :sommets){
            int idCouleurX=x.getIdCouleur();
            if(x.haveDefaultColor()) return false;
            for(Sommet succ:x.getSuccesseurs()){
                int idCouleurSucc=succ.getIdCouleur();
                if(idCouleurSucc==idCouleurX)
                    return false;
            }
        }
        return true;
    }

    public int nombreDeCouleursUtilisees(){
        ArrayList<Integer> couleurs=new ArrayList<>();
        for (Sommet x:
             sommets) {
            if(!x.haveDefaultColor()){
                int idCouleurX=x.getIdCouleur();
                if(!couleurs.contains(idCouleurX)){
                    couleurs.add(idCouleurX);
                }
            }
        }
        return couleurs.size();
    }
    /////////





    public void printGraphe(boolean writeOnConsole){
        StringBuilder contenuFichier = new StringBuilder();

        contenuFichier.append("Nom: "+getNom()+"\n");
        contenuFichier.append("Oriente(non/oui): ");
        contenuFichier.append(estOriente?"oui":"non"+"\n");
        contenuFichier.append("NbSommets: "+sommets.size()+"\n");
        //NbValSommet
        contenuFichier.append("NbArcs: "+getNombreDarc()+"\n");
        //NbValArc

        contenuFichier.append("--- Liste des sommets\n");
        for(int i=0;i<sommets.size();i++){
            Sommet s=sommets.get(i);
            contenuFichier.append(i+" "+s.getNom()+"\n");
        }

        contenuFichier.append("--- Liste des aretes\n");
        for(Sommet s:sommets){
            for(Sommet succ : s.getSuccesseurs()){
                contenuFichier.append(s.getId()+" "+succ.getId()+"\n");
            }
        }

        if(writeOnConsole) System.out.println(contenuFichier.toString());

        try(PrintWriter out=new PrintWriter("fichiersSauvegarde/"+getNom()+".txt")){
            out.println(contenuFichier.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getNombreDarc(){
        int compteur=0;
        for(Sommet s: sommets){
            compteur+=s.getNombreDeSuccesseurs();
        }
        return compteur/2; //car les arcs sont comptés 2 fois
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
