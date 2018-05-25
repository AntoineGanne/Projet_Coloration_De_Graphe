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

            //g.greedyColoring();
            //g.WelshPowell();
            g.DSATUR();

            if(g.ColorationEstCorrecte()){
                System.out.println("La coloration est correcte!");
                System.out.println("Nombre de couleurs utilisées: "+g.nombreDeCouleursUtilisees());
            }else{
                System.out.println("La coloration est incorrecte");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
