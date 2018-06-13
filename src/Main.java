import Modele.Graphe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graphe g;
        int choix;
        double start, end;

        try {

            Scanner sc = new Scanner(System.in);


            System.out.println("Veuillez entrer le nom du fichier à tester");
            String nomFichier = sc.next();

            if (!nomFichier.contains(".txt"))
                g = FileReader.lectureGrapheDepuisFichier("fichiersTest/" + nomFichier + ".txt");
            else
                g = FileReader.lectureGrapheDepuisFichier("fichiersTest/" + nomFichier);

         //   g=grapheGenerator.generationGraphe(100,4000,false);

            //g.testerToutesLesColorations();

            System.out.println("Veuillez choisir une méthode");
            System.out.println("1 - Greedy");
            System.out.println("2 - WelshPowell");
            System.out.println("3 - DSatur");
            System.out.println("Autre - Quitter");
            choix = sc.nextInt();

            g.coloration(choix,true);
            /*
            switch(choix) {
                case 1:
                    start = System.nanoTime();
                    g.greedyColoring();
                    end = System.nanoTime();
                    System.out.println("Temps d'exécution = "+(end-start)/1000000+ " ms");
                    break;
                case 2:
                    start = System.nanoTime();
                    g.WelshPowell();
                    end = System.nanoTime();
                    System.out.println("Temps d'exécution = "+(end-start)/1000000+ " ms");
                    break;
                case 3:
                    start = System.nanoTime();
                    g.DSATUR();
                    end = System.nanoTime();
                    System.out.println("Temps d'exécution = "+(end-start)/1000000+ " ms");
                    break;
            }


            if(g.ColorationEstCorrecte()){
                System.out.println("La coloration est correcte!");
                System.out.println("Nombre de couleurs utilisées: "+g.nombreDeCouleursUtilisees());
            }else{
                System.out.println("La coloration est incorrecte");
            }
            */

            //g.printGraphe(true);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
