
public class App {
    public static void main(String[] args) throws Exception {
        
        
        VeIA ia = new VeIA();
        VeIA ia2 = new VeIA();
        System.out.println(ia.jogue(".........", ".........".replace(".", "").length()));
        System.out.println(ia2.jogue("....O....", "....O....".replace(".", "").length()));
        System.out.println(ia.jogue("X...O....", "X...O....".replace(".", "").length()));
        System.out.println(ia2.jogue("X.O.O....", "X.O.O....".replace(".", "").length()));
        //System.out.println(ia.jogue("XOX.O.O..", "XOX.O.O..".replace(".", "").length()));   
        //mapa_vitoria[random.nextInt(c)].printMatriz();
        //mapa_empate[random.nextInt(z)].printMatriz();
        //mapa_vitoria[i].printMatriz();
        //System.out.println();

    }
}
