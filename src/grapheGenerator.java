import Modele.Graphe;

public class grapheGenerator {
    /**
     * génère un graphe aléatoire.
     * les arètes sont crées en selectionnant 2 sommets aléatoire (loi uniforme sur [0,nbSommets] )
     * @param nbSommets
     * @param nbAretes
     * @param estOriente vrai si le graphe est orienté
     * @return
     */
    public static Graphe generationGraphe(int nbSommets,int nbAretes, boolean estOriente){
        Graphe grapheGenerated=new Graphe(estOriente);

        //ajout des sommets
        for(int i=0;i<nbSommets;i++){
            grapheGenerated.ajouterSommet("noeud " + i);
        }
        //ajout des aretes
        for(int i=0;i<nbAretes;i++){
            boolean succesCreationArete=false;
            do{  // on selectionne aléatoirement 2 sommets qui ne sont pas voisin et on les relie
                int indexSommetInitial= (int) Math.floor(Math.random()*nbSommets);
                int indexSommetFinal;
                do{  // on ne veut pas d'aretes d'un sommet vers lui-même
                    indexSommetFinal=(int) Math.floor(Math.random()*nbSommets);
                }while(indexSommetFinal==indexSommetInitial);
                if(!grapheGenerated.areteExisteEntreSommets(indexSommetInitial,indexSommetFinal)){
                    try {
                        grapheGenerated.ajouterArc(indexSommetInitial,indexSommetFinal);
                        if(!estOriente){ //si il n'est pas orienté, on ajoute l'arete en sens inverse
                            grapheGenerated.ajouterArc(indexSommetFinal,indexSommetInitial);
                        }

                        succesCreationArete=true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }while (!succesCreationArete);

        }


        return grapheGenerated;
    }
}
