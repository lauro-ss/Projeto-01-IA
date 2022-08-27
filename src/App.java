import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        
        
        VeiaIA ia = new VeiaIA();
        System.out.println(ia.jogue("X..O...O.", 3));    
        //mapa_vitoria[random.nextInt(c)].printMatriz();
        //mapa_empate[random.nextInt(z)].printMatriz();
        //mapa_vitoria[i].printMatriz();
        //System.out.println();

    }
}
