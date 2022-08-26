import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class App {
    public static void main(String[] args) throws Exception {
        String caminho = System.getProperty("user.dir") + "\\CSV\\grafo.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "UTF-8"));
        String linha;
        Vertex vertex[] = new Vertex[5477];
        Vertex mapa_vitoria[] = new Vertex[5477];
        Vertex mapa_empate[] = new Vertex[5477];
        Random random = new Random();
        int i = 0;
        
        /* carrega todos os estados */
        while((linha = br.readLine()) != null) { 
            vertex[i] = new Vertex(linha.split(";")[0]);
            i++;
        }
        br.close();

        String estado_inicial = "........O";
        int grau;
        int c = 0;
        int z = 0;
        for(i = 0; i < 5477; i++){
            grau = vertex[i].matches(estado_inicial);
            if(grau > 0 && vertex[i].vencedorX == true){
                mapa_vitoria[c] = new Vertex(vertex[i].label);
                c++;
            }else if(grau > 0 && vertex[i].empate == true){
                mapa_empate[z] = new Vertex(vertex[i].label);
                z++;
            }
        }
            
            mapa_vitoria[random.nextInt(c)].printMatriz();
            mapa_empate[random.nextInt(z)].printMatriz();
            //mapa_vitoria[i].printMatriz();
            //System.out.println();
        

    }
}
