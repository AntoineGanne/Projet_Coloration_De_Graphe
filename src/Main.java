import Modele.Graphe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graphe g;

        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Veuillez entrer le nom du fichier à tester");
            String nomFichier = sc.next();

            if (!nomFichier.contains(".txt"))
                g = FileReader.lectureGrapheDepuisFichier("fichiersTest/" + nomFichier + ".txt");
            else
                g = FileReader.lectureGrapheDepuisFichier("fichiersTest/" + nomFichier);

            double start = System.nanoTime();
            g.greedyColoring();
            //g.WelshPowell();
            //g.DSATUR();
            double end = System.nanoTime();
            System.out.println("Temps d'exécution = "+(end-start)/1000000+ " ms");


            if(g.ColorationEstCorrecte()){
                System.out.println("La coloration est correcte!");
                System.out.println("Nombre de couleurs utilisées: "+g.nombreDeCouleursUtilisees());
            }else{
                System.out.println("La coloration est incorrecte");
            }

            g.printGraphe(true);

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
