import Modele.Graphe;

public class Main {

    public static void main(String[] args) {

        Graphe g=FileReader.lectureGrapheDepuisFichier("fichiersTest/queen5_5.txt");
        try {
            //g.greedyColoring();
            g.WelshPowell();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(g.ColorationEstCorrecte()){
            System.out.println("La coloration est correcte!");
            System.out.println("Nombre de couleurs utilisées: "+g.nombreDeCouleursUtilisees());
        }else{
            System.out.println("La coloration est incorrecte");
        }


    }
}
