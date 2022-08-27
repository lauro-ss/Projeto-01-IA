import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class VeiaIA {
    
    private Vertex vertex[] = new Vertex[5477];
    private Vertex mapa_vitoria[] = new Vertex[5477];
    private Vertex mapa_empate[] = new Vertex[5477];
    private Vertex mapa_prox_jogada[] = new Vertex[5477];

    private char meuCaractere;

    private int i, c_vitorias = 0, c_empates = 0, c_jogadas = 0;
    private int grau;

    public VeiaIA() throws IOException{
        int i = 0;
        String linha;
        String caminho = System.getProperty("user.dir") + "\\CSV\\grafo.csv";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho), "UTF-8"));
        /* carrega todos os estados */
        while((linha = br.readLine()) != null) { 
            vertex[i] = new Vertex(linha.split(";")[0], Integer.parseInt(linha.split(";")[1].split("\\.")[1]));
            i++;
        }
        br.close();
    }
    /***
     * Chame esse metódo se quiser que a IA comece jogando
     * e por padrao ela ira ser o caractere 'O'
     */
    public void start(){
        c_vitorias = 0; 
        c_empates = 0;
        this.meuCaractere = 'O';
        for(i = 0; i < 5477; i++){
            grau = vertex[i].matches(".........");
            if(grau >= 0 && vertex[i].vencedorO == true){
                mapa_vitoria[c_vitorias] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                c_vitorias++;
            }else if(grau >= 0 && vertex[i].empate == true){
                mapa_empate[c_empates] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                c_empates++;
            }
        }
    }
    /**
     * O metodo jogue retorna um inteiro representado a posicao que a IA jogou
     * @param label o estado atual do jogo em forma de string - ex '........O','X........'
     * @param numJogadas o numero da jogada ou de caracteres na board, no caso do ex do label - 1
     * @return
     */
    public int jogue(String label, int numJogadas){
        if(numJogadas == 1){
            c_vitorias = 0; 
            c_empates = 0;
            c_jogadas = 0;
            this.meuCaractere = 'X';
            for(i = 0; i < 5477; i++){
                grau = vertex[i].matches(label);
                if(grau >= 0 && vertex[i].vencedorX == true){
                    mapa_vitoria[c_vitorias] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                    c_vitorias++;
                }else if(grau >= 0 && vertex[i].empate == true){
                    mapa_empate[c_empates] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                    c_empates++;
                }
            }
            
            for(i = 0; i < 5477; i++){
                grau = vertex[i].matches(label);
                if(grau == numJogadas && vertex[i].numJogadas == numJogadas+1){
                    vertex[i].printMatriz();
                    System.out.println();
                }
            }
        }else{
            c_jogadas = 0;
            for(i = 0; i < 5477; i++){
                grau = vertex[i].matches(label);
                if(grau == numJogadas && vertex[i].numJogadas == numJogadas+1){
                    vertex[i].printMatriz();
                    System.out.println();
                }
            }
        }
        return 0;
    }
}
