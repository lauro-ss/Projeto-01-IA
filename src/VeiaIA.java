import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class VeiaIA {
    
    private Vertex vertex[] = new Vertex[5477];
    private Vertex mapa_vitoria[] = new Vertex[5477];
    private Vertex mapa_derrota[] = new Vertex[5477];
    private Vertex mapa_empate[] = new Vertex[5477];
    private Vertex mapa_prox_jogada[] = new Vertex[5477];
    private Vertex mapa_atual;

    private char meuCaractere;
    private char opCaractere;

    private int i, c_vitorias = 0, c_empates = 0, c_jogadas = 0, c_derrotas = 0;
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
    public int start(){
        c_vitorias = 0; 
        c_empates = 0;
        c_derrotas = 0;
        this.meuCaractere = 'O';
        this.opCaractere = 'X';
        for(i = 0; i < 5477; i++){
            grau = vertex[i].matches(".........");
            if(grau >= 0 && vertex[i].vencedorO == true){
                mapa_vitoria[c_vitorias] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                c_vitorias++;
            }else if(grau >= 0 && vertex[i].empate == true){
                mapa_empate[c_empates] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                c_empates++;
            }else if(grau >= 0 && vertex[i].vencedorX == true){
                mapa_derrota[c_derrotas] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                c_derrotas++;
            }
        }
        //posicao do meio do tabuleiro, a IA sempre vai comecar jogando no meio
        return 4;
    }
    /**
     * O metodo jogue retorna um inteiro representado a posicao que a IA jogou
     * @param label o estado atual do jogo em forma de string - ex '........O','X........'
     * @param numJogadas o numero da jogada ou de caracteres na board, no caso do ex do label - 1
     * @return retorna a jogada entre 0 e 8, ou -1 caso exista algum erro
     */
    public int jogue(String label, int numJogadas){
        if(numJogadas == 1){
            c_vitorias = 0; 
            c_empates = 0;
            c_jogadas = 0;
            this.meuCaractere = 'X';
            this.opCaractere = 'O';
            for(i = 0; i < 5477; i++){
                grau = vertex[i].matches(label);
                if(grau >= 0 && vertex[i].vencedorX == true){
                    mapa_vitoria[c_vitorias] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                    c_vitorias++;
                }else if(grau >= 0 && vertex[i].empate == true){
                    mapa_empate[c_empates] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                    c_empates++;
                }else if(grau >= 0 && vertex[i].vencedorO == true){
                    mapa_derrota[c_derrotas] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                    c_derrotas++;
                }
            }

            for(i = 0; i < 5477; i++){
                grau = vertex[i].matches(label);
                if(grau == numJogadas && vertex[i].numJogadas == numJogadas+1){
                    mapa_prox_jogada[c_jogadas] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                    c_jogadas++;
                }
            }
            /*for(i = 0; i < c_jogadas; i++){
                for(int c = 0; c < c_vitorias; c++){
                    grau = mapa_prox_jogada[i].matches(mapa_vitoria[c].label);
                    if(grau == 2 && mapa_vitoria[c].numJogadas <= 6){
                        mapa_prox_jogada[i].printMatriz();
                        System.out.println();
                        mapa_vitoria[c].printMatriz();
                        System.out.println();
                    }
                }
            }*/
            for(i = 0; i < c_jogadas; i++){
                // peso maior no meio
                if(mapa_prox_jogada[i].label.charAt(4) == meuCaractere)
                    return 4;
            }
            // peso medio nas bordas
            for(i = 0; i < c_jogadas; i++){
                for(int c = 0; c < 9; c++){
                    if(c%2 == 0 && mapa_prox_jogada[i].label.charAt(c) == meuCaractere)
                        return c;
                }
            }
            // peso baixo nos tracados
            for(i = 0; i < c_jogadas; i++){
                for(int c = 0; c < 9; c++){
                    if(c%2 != 0 && mapa_prox_jogada[i].label.charAt(c) == meuCaractere)
                        return c;
                }
            }
        }else{
            if(numJogadas%2 != 0){
                /** aqui */
                c_vitorias = 0; 
                c_empates = 0;
                c_jogadas = 0;
                this.meuCaractere = 'X';
                this.opCaractere = 'O';
                for(i = 0; i < 5477; i++){
                    grau = vertex[i].matches(label);
                    if(grau >= 0 && vertex[i].vencedorX == true){
                        mapa_vitoria[c_vitorias] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_vitorias++;
                    }else if(grau >= 0 && vertex[i].empate == true){
                        mapa_empate[c_empates] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_empates++;
                    }else if(grau >= 0 && vertex[i].vencedorO == true){
                        mapa_derrota[c_derrotas] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_derrotas++;
                    }
                }
                /** aqui */
                for(i = 0; i < 5477; i++){
                    grau = vertex[i].matches(label);
                    if(grau == numJogadas && vertex[i].numJogadas == numJogadas+1){
                        mapa_prox_jogada[c_jogadas] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_jogadas++;
                    }
                }
                //*verifica se o proximo estado e o de vitoria*/
                for(i = 0; i < c_jogadas; i++){
                    for(int c = 0; c < c_vitorias; c++){
                        grau = mapa_prox_jogada[i].matches(mapa_vitoria[c].label);
                        if(grau == numJogadas && mapa_vitoria[c].numJogadas == numJogadas+1){
                            for(int z = 0; z < 9; z++){
                                if(label.charAt(z) == meuCaractere ^ mapa_vitoria[c].label.charAt(z) == meuCaractere){
                                    return z;
                                }
                            }
                        }
                    }
                }
                //*verifica qual o proximo melhor estado*/
                for(i = 0; i < c_jogadas; i++){
                    for(int c = 0; c < c_derrotas; c++){
                        grau = mapa_prox_jogada[i].matches(mapa_derrota[c].label);
                        if(grau == numJogadas+1 && mapa_derrota[c].numJogadas == numJogadas+2){
                            //mapa_prox_jogada[i].printMatriz();
                            //System.out.println();
                            //mapa_derrota[c].printMatriz();
                            //System.out.println();
                            //"X..OO...."
                            //"XX.OOO..."
                            //System.out.println(mapa_derrota[c].label);
                            for(int z = 0; z < 9; z++){
                                if(label.charAt(z) == '.' && mapa_derrota[c].label.charAt(z) == opCaractere){
                                    return z;
                                }
                            }
                        }
                    }
                }
                // joga no melhor cenario com o maior peso
                for(i = 0; i < c_jogadas; i++){
                    for(int c = 0; c < 9; c++){
                        if(c%2 == 0 && mapa_prox_jogada[i].label.charAt(c) == meuCaractere)
                            return c;
                    }
                }
            }if(numJogadas%2 == 0){
                /** aqui */
                c_vitorias = 0; 
                c_empates = 0;
                c_jogadas = 0;
                this.meuCaractere = 'O';
                this.opCaractere = 'X';
                for(i = 0; i < 5477; i++){
                    grau = vertex[i].matches(label);
                    if(grau >= 0 && vertex[i].vencedorO == true){
                        mapa_vitoria[c_vitorias] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_vitorias++;
                    }else if(grau >= 0 && vertex[i].empate == true){
                        mapa_empate[c_empates] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_empates++;
                    }else if(grau >= 0 && vertex[i].vencedorX == true){
                        mapa_derrota[c_derrotas] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_derrotas++;
                    }
                }
                /** aqui */
                for(i = 0; i < 5477; i++){
                    grau = vertex[i].matches(label);
                    if(grau == numJogadas && vertex[i].numJogadas == numJogadas+1){
                        mapa_prox_jogada[c_jogadas] = new Vertex(vertex[i].label, vertex[i].numJogadas);
                        c_jogadas++;
                    }
                }
                //*verifica se o proximo estado e o de vitoria*/
                for(i = 0; i < c_jogadas; i++){
                    for(int c = 0; c < c_vitorias; c++){
                        grau = mapa_prox_jogada[i].matches(mapa_vitoria[c].label);
                        if(grau == numJogadas && mapa_vitoria[c].numJogadas == numJogadas+1){
                            for(int z = 0; z < 9; z++){
                                if(label.charAt(z) == meuCaractere ^ mapa_vitoria[c].label.charAt(z) == meuCaractere){
                                    return z;
                                }
                            }
                        }
                    }
                }
                //*verifica se o oponente vence nas proximos estado*/
                for(i = 0; i < c_jogadas; i++){
                    for(int c = 0; c < c_derrotas; c++){
                        grau = mapa_prox_jogada[i].matches(mapa_derrota[c].label);
                        if(grau == numJogadas+1 && mapa_derrota[c].numJogadas == numJogadas+2){
                            //mapa_prox_jogada[i].printMatriz();
                            //System.out.println();
                            //mapa_derrota[c].printMatriz();
                            //System.out.println();
                            //"X...O..O."
                            //"XOX.O..O."
                            //System.out.println(mapa_derrota[c].label);
                            for(int z = 0; z < 9; z++){
                                if(label.charAt(z) == '.' && mapa_derrota[c].label.charAt(z) == opCaractere){
                                    return z;
                                }
                            }
                        }
                    }
                }
                // peso medio nas bordas
                for(i = 0; i < c_jogadas; i++){
                    for(int c = 0; c < 9; c++){
                        if(c%2 == 0 && mapa_prox_jogada[i].label.charAt(c) == meuCaractere)
                            return c;
                    }
                }
            }
        }
        return -1;
    }
}
