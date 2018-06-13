import Modele.Graphe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graphe g;
        int choix;
        double start, end;
        boolean lecture = true;
        String dossierFichiers="fichiersSauvegarde/";//"fichiersTest/"

        while(lecture) {
            try {

                Scanner sc = new Scanner(System.in);

                System.out.println("Veuillez entrer le nom du fichier à tester");
                String nomFichier = sc.next();

                if (!nomFichier.contains(".txt"))
                    g = FileReader.lectureGrapheDepuisFichier( dossierFichiers+ nomFichier + ".txt");
                else
                    g = FileReader.lectureGrapheDepuisFichier(dossierFichiers + nomFichier);

                //   g=grapheGenerator.generationGraphe(100,4000,false);

                //g.testerToutesLesColorations();

                System.out.println("Veuillez choisir une méthode");
                System.out.println("1 - Greedy");
                System.out.println("2 - WelshPowell");
                System.out.println("3 - DSatur");
                System.out.println("Autre - Quitter");
                choix = sc.nextInt();

                g.coloration(choix, true);
                System.out.println("Voulez vous refaire un test ? (O/N)");
                String res = sc.next();

                switch(res){
                    case "O":
                        lecture = true;
                        break;
                    case "N":
                        lecture = false;
                        break;
                    default:
                        lecture = false;
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
