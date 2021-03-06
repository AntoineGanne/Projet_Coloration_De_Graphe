import Modele.Graphe;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Tests{
    private static String CRLF="\r\n";
    private static String TAB="\t";
    private static String dossierOutput="valeursExcel/";

    public static void main(String[] args){
        //rappel perso: si graphe avec n sommets alors nb max d'aretes= n(n-1)/2
        //generationTableau(1000,10000,400000,400000,100,dossierOutput);
        //testComplexiteEtNbCouleursSurNbAretesVariables(1000);
        testComplexiteEtNbCouleursSurNbAretesVariables(501);

    }

    /**
     * génère 3 fichiers (un pour chaque algorithme) qui contiennent les temps d'execution  pour des grapges aléatoires
     * de nombre de sommets et d'arètes specifiés.
     * resultats dans les fichiers greedy, WP et DSATUR du dossier valeursExcel
     * @param nbMinSommet
     * @param nbMaxSommet
     * @param nbMinAretes
     * @param nbMaxAretes
     * @param incrementation
     * @param nomFichierOutput
     */
    public static void generationTableau(int nbMinSommet, int nbMaxSommet, int nbMinAretes, int nbMaxAretes, int incrementation, String nomFichierOutput){


        StringBuilder fileStringGreedy = new StringBuilder();
        StringBuilder fileStringWP = new StringBuilder();
        StringBuilder fileStringDSATUR = new StringBuilder();

        fileStringGreedy.append("nb Sommet \\ nbAretes").append(TAB);
        fileStringWP.append("nb Sommet \\ nbAretes").append(TAB);
        fileStringDSATUR.append("nb Sommet \\ nbAretes").append(TAB);

        for(int nbArete=nbMinAretes;nbArete<=nbMaxAretes;nbArete+=incrementation){
            fileStringGreedy.append(nbArete).append(TAB);
            fileStringWP.append(nbArete).append(TAB);
            fileStringDSATUR.append(nbArete).append(TAB);
        }
        fileStringGreedy.append(CRLF);
        fileStringWP.append(CRLF);
        fileStringDSATUR.append(CRLF);

        for(int nbSommets=nbMinSommet;nbSommets<=nbMaxSommet;nbSommets+=incrementation){
            fileStringGreedy.append(nbSommets).append(TAB);
            fileStringWP.append(nbSommets).append(TAB);
            fileStringDSATUR.append(nbSommets).append(TAB);

            for(int nbArete=nbMinAretes;nbArete<=nbMaxAretes;nbArete+=incrementation){
                System.out.println("nbSommet: "+nbSommets+" ; nbAretes: "+nbArete);
                Graphe g=grapheGenerator.generationGraphe(nbSommets,nbArete,false);
                try {
                    double tempsExecGreedy=g.coloration(1,false);
                    double tempsExecWP=g.coloration(2,false);
                    double tempsExecDSATUR=g.coloration(3,false);

                    fileStringGreedy.append((int)tempsExecGreedy).append(TAB);
                    fileStringWP.append((int)tempsExecWP).append(TAB);
                    fileStringDSATUR.append((int)tempsExecDSATUR).append(TAB);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            fileStringGreedy.append(CRLF);
            fileStringWP.append(CRLF);
            fileStringDSATUR.append(CRLF);
        }


        try(PrintWriter out=new PrintWriter(nomFichierOutput+"greedy.txt")){
            out.println(fileStringGreedy.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try(PrintWriter out=new PrintWriter(nomFichierOutput+"WP.txt")){
            out.println(fileStringWP.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try(PrintWriter out=new PrintWriter(nomFichierOutput+"DSATUR.txt")){
            out.println(fileStringDSATUR.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * teste l'effet du nombre d'aretes sur le temps d'execution des 3 algos.
     * resultats dans le fichier valeursExcel/complexitéArete.txt
     * @param nbSommets
     */
    public static void testComplexiteEtNbCouleursSurNbAretesVariables(int nbSommets){
        Graphe g=grapheGenerator.generationGraphe(nbSommets,0,false);
        int nbAretesMax=(nbSommets-1)*nbSommets/2;
        int inc=nbAretesMax/100;
        int nbAretesGraphe=0;

        StringBuilder fileStringTempsExec = new StringBuilder();
        fileStringTempsExec.append("nombre de sommets: ").append(nbSommets).append(CRLF);
        fileStringTempsExec.append("nombre d'aretes").append(TAB).append("greedy").append(TAB).append("WP").append(TAB).append("DSATUR");
        fileStringTempsExec.append(CRLF);

        StringBuilder fileStringNbCouleurs = new StringBuilder();
        fileStringNbCouleurs.append("nombre de sommets: ").append(nbSommets).append(CRLF);
        fileStringNbCouleurs.append("nombre d'aretes").append(TAB).append("greedy").append(TAB).append("WP").append(TAB).append("DSATUR");
        fileStringNbCouleurs.append(CRLF);

        for(int perCentOfAretes=0;perCentOfAretes<=100;perCentOfAretes+=10){
            while(nbAretesGraphe<nbAretesMax*perCentOfAretes/100){
                g.ajouterArcAleatoire();
                nbAretesGraphe++;
            }

            // on imprime le graphe
            String nomGraphe="Sommets"+nbSommets+"_Aretes"+nbAretesGraphe;
            g.setNom(nomGraphe);
            g.printGraphe(false);

            System.out.println(nbAretesGraphe);

            try {
                g.coloration(1,false);
                double tempsExecDSATUR = g.coloration(3, false);

                int nbCouleursGreedy=g.nombreDeCouleursUtilisees();
                g=FileReader.lectureGrapheDepuisFichier("fichiersSauvegarde/"+nomGraphe+".txt");
                double tempsExecWP = g.coloration(2, false);
                int nbCouleursWP=g.nombreDeCouleursUtilisees();
                g=FileReader.lectureGrapheDepuisFichier("fichiersSauvegarde/"+nomGraphe+".txt");
                double tempsExecGreedy = g.coloration(1, false);
                int nbCouleursDSATUR=g.nombreDeCouleursUtilisees();

                fileStringTempsExec.append(perCentOfAretes).append(TAB);
                fileStringTempsExec.append((int)tempsExecGreedy).append(TAB);
                fileStringTempsExec.append((int)tempsExecWP).append(TAB);
                fileStringTempsExec.append((int)tempsExecDSATUR).append(TAB);

                fileStringNbCouleurs.append(perCentOfAretes).append(TAB);
                fileStringNbCouleurs.append((int)nbCouleursGreedy).append(TAB);
                fileStringNbCouleurs.append((int)nbCouleursWP).append(TAB);
                fileStringNbCouleurs.append((int)nbCouleursDSATUR).append(TAB);

            } catch (Exception e) {
                e.printStackTrace();
            }

            fileStringTempsExec.append(CRLF);
            fileStringNbCouleurs.append(CRLF);

        }



        try(PrintWriter out=new PrintWriter(dossierOutput+"complexitéArete_"+nbSommets+".txt")){
            out.println(fileStringTempsExec.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try(PrintWriter out=new PrintWriter(dossierOutput+"ComparaisonNbCouleurs_"+nbSommets+".txt")){
            out.println(fileStringNbCouleurs.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
