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
            grapheGenerated.ajouterArcAleatoire();
        }


        return grapheGenerated;
    }
}
