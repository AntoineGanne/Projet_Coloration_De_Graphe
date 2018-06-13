import Modele.Graphe;

public class grapheGenerator {
    public static void main(String[] args){
        int n=1000;
        int m=399600;
        Graphe g=generationGraphe(n,m,false);
        //Graphe g=generationGrapheComplet(n,false);

        String nomGraphe="Sommets"+n+"_Aretes"+m;
        g.setNom(nomGraphe);
        g.printGraphe(false);
    }

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

        if(estOriente && nbAretes>nbSommets*(nbSommets-1)/2){
            System.out.println("tentative de création de graphe contenznt trop d'aretes!");
            return grapheGenerated;
        }

        //ajout des sommets
        for(int i=0;i<nbSommets;i++){
            grapheGenerated.ajouterSommet("noeud" + i);
        }
        //ajout des aretes
        for(int i=0;i<nbAretes;i++){
            grapheGenerated.ajouterArcAleatoire();
        }


        return grapheGenerated;
    }

    public static Graphe generationGrapheComplet(int nbSommets, boolean estOriente){
        Graphe grapheGenerated=new Graphe(estOriente);
        //ajout des sommets
        for(int i=0;i<nbSommets;i++){
            grapheGenerated.ajouterSommet("noeud" + i);
        }
        //ajout des aretes
        for(int i=0;i<nbSommets;i++){
            for(int j=i+1;j<nbSommets;j++){
                try {
                    grapheGenerated.ajouterArc(i,j);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return grapheGenerated;
    }

}
