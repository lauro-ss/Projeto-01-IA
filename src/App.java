import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        String caminho = System.getProperty("user.dir") + "\\CSV\\grafo.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "UTF-8"));
        String linha;
        Vertex vertex[] = new Vertex[5477];
        int i = 0;
        
        /* carrega todos os estados */
        while((linha = br.readLine()) != null) { 
            vertex[i] = new Vertex(linha.split(";")[0]);
            i++;
        }
        br.close();

        String estado_inicial = "X.......O";

        for(i = 0; i < 5477; i++){
            if(vertex[i].matches(estado_inicial) > 1 && vertex[i].vencedorO == true){
                vertex[i].printMatriz();
                System.out.println();
            }
        }

    }
}
