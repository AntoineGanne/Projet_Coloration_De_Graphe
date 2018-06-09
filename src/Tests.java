import Modele.Graphe;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Tests{
    private static String CRLF="\r\n";
    private static String TAB="\t";
    private static String dossierOutput="valeursExcel/";

    public static void main(String[] args){
        //rappel perso: si graphe avec n sommets alors nb max d'aretes= n(n-1)/2
        generationTableau(1000,10000,400000,400000,100,dossierOutput);
    }


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
}
