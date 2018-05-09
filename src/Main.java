import Modele.Graphe;

public class Main {

    public static void main(String[] args) {

        Graphe g=FileReader.lectureGrapheDepuisFichier("fichiersTest/crown10.txt");
        try {
            g.greedyColoring();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(g.ColorationEstCorrecte()){
            System.out.println("La coloration est correcte!");
        }else{
            System.out.println("La coloration est incorrecte");
        }


    }
}
