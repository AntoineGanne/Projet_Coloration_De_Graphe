import Modele.Graphe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

public class FileReader {

    static public Graphe lectureGrapheDepuisFichier(String nomFichier) throws FileNotFoundException {
        Graphe grapheGenerated=new Graphe();
        int nbSommets=-1;
        int nbValSommet=-1;
        int nbArcs=-1;
        int nbValArc=-1;
        boolean fichierEnLecture=true;
        try{
            File fichier=new File(nomFichier);
            //BufferedReader reader=new BufferedReader(new java.io.FileReader(fichier));
            Scanner sc=new Scanner(fichier);
            //nom du graphe
            while (fichierEnLecture){
//                System.out.println(sc.next());
                String next=sc.next();


                switch (next){
                    case "Nom:":
                        grapheGenerated.setNom(sc.next());
                        break;
                    case "Oriente(non/oui):":
                        boolean reponse=sc.next().equals("oui")? true:false;
                        grapheGenerated.setEstOriente(reponse);
                        break;
                    case "NbSommets:":
                        nbSommets=sc.nextInt();
                        break;
                    case "NbValSommet:":
                        nbValSommet=sc.nextInt();
                        break;
                    case "NbArcs:":
                        nbArcs=sc.nextInt();
                        break;
                    case "NbValArc:":
                        nbValArc=sc.nextInt();
                        break;
                    case "---":
                        String nextLine=sc.nextLine();
                        if(nextLine.contains("Liste des sommets")){
                            try {
                                if(nbSommets==-1){
                                    throw new IOException("errreur, lecture des sommets. nbSommets non defini");
                                }
                                lectureSommets(grapheGenerated,sc,nbSommets);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(nextLine.contains("Liste des aretes")){
                            try {
                                if(nbArcs==-1){
                                    throw new IOException("errreur, lecture des aretes. nbAretes non defini");
                                }
                                lectureAretes(grapheGenerated,sc,nbArcs);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        break;
                     default:
                         System.out.println(next);
                         throw new IOException("Probleme lecture fichier");
                }

                fichierEnLecture=sc.hasNext();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Ce fichier n'existe pas. Veuillez recommencer svp");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return grapheGenerated;
    }

    static void lectureSommets(Graphe g, Scanner sc,int nbSommets) throws IOException {
        for(int i=0;i<nbSommets;i++){
            if(sc.nextInt()==i){
                g.ajouterSommet(sc.next());
            }else{
                throw new IOException("errreur, lecture des sommets");
            }
        }
    }

    static void lectureAretes(Graphe g, Scanner sc,int nbAretes) throws IOException{
        for(int i=0;i<nbAretes;i++){
            try{
                int indexSommetInitial=sc.nextInt();
                int indexSommetFinal=sc.nextInt();
                g.ajouterArc(indexSommetInitial,indexSommetFinal);

            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
