package buscaminas;

import java.util.*;

public class DibujaMapa {
    
    public static String[][] mapa;
    public static int minas = 0;
    public static int minasFijo = 0;
    public static List<int[]> casillasDescubiertas = new ArrayList<int[]>();
   
    public static void main(String[] args) {
        
        dibujarMapa();
        
    }
    
    public static void dibujarMapa() {
        
        Scanner lector = new Scanner (System.in);
        
        int filas = 0;
        int columnas = 0;
       
        while (minas <= 0 || minas > 20) {
        
            System.out.println("¿Cuántas minas quieres? (Con un máximo de 20 minas): ");

            while (!lector.hasNextInt()){
                
                System.out.println("Te he pillado Nacho, dame un número de verdad (como pongas un negativo... te meto)");
                lector.next();
                
            }
            
            minas = lector.nextInt();
            minasFijo = minas;
            
            if (minas<=5 && minas>0){
               filas = 5;
               columnas = 5;
            }
            
            if (minas>20){
                System.out.println("No pongas tantas minas alelao");
            }

            if (minas >5 && minas<=10) {
                
              filas = 6;
              columnas = 6;
            }
            
            if (minas >10 && minas<=20) {

              filas = 7;
              columnas = 7;
              
            }
        }
        
       mapa = crearMapa(filas,columnas);
       imprimirMapa(mapa);
    }     

    public static String[][] crearMapa(int filas,int columnas){

        String [][] mapaOculto = new String[filas][columnas];

        int numaX;
        int numaY;
        int contadorMina;

        for (int i = 0; i<mapaOculto.length;i++) { //Bucles asignar los "*" al mapa

            for (int j = 0;j<mapaOculto[0].length;j++){

            mapaOculto[i][j] = "*";

            }
        }

        while (minas > 0) { // Bucle para asignar las minas

            numaX = (int) (Math.random()*columnas);
            numaY = (int) (Math.random()*filas);

            if (mapaOculto[numaX][numaY].equals("*")) {

                mapaOculto[numaX][numaY] = "M";
                minas -= 1;
            }
        }

        for (int y = 0; y<mapaOculto.length;y++) { //Bucle para recorrer y contar las minas por casilla

            for (int x = 0;x<mapaOculto[0].length;x++){

                if (!mapaOculto[y][x].equals("M")&&!mapaOculto[y][x].equals("10")){

                    if (y == 0 && x == 0) { // Esquina superior izq

                        contadorMina = 0;

                        for (int i = 0; i<=1;i++ ){

                            for (int j = 0; j<=1;j++){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    }

                    else if (y == 0 && x == mapaOculto[x].length-1) { //esquina superior derecha

                        contadorMina = 0;

                        for (int i = y; i<=y+1;i++){ 

                            for (int j = x; j>=x-1;j--){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = ""+contadorMina;

                    }

                    else if (y == mapaOculto[y].length-1 && x == 0){ //esquina inferior izq

                        contadorMina = 0;

                        for (int i = y; i>=y-1;i--){

                            for (int j = x; j<=x+1;j++){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    }

                    else if (y == mapaOculto[y].length-1 && x == mapaOculto[x].length-1){ //esquina inferior derecha

                        contadorMina = 0;

                        for (int i = y; i>=y-1;i-- ){

                            for (int j = x; j>=x-1;j--){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    }

                    else if (y > 0 && x == 0 && y < mapaOculto[y].length-1){ //columna izq

                        contadorMina = 0;

                        for (int i = y-1; i<=y+1;i++ ){

                            for (int j = 0; j<=1;j++){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    }

                    else if (y == 0 && x > 0 && x < mapaOculto[x].length-1){ //fila superior

                        contadorMina = 0;

                        for (int j = x-1; j<=x+1;j++ ){

                            for (int i = 0; i<=1; i++){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    }

                    else if (x == mapaOculto[x].length-1 && y > 0 && y < mapaOculto[y].length-1){ //culumna del final

                        contadorMina = 0;

                        for (int i = y-1; i<=y+1;i++ ){

                            for (int j = x-1; j<=x;j++){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    } 

                    else if (y == mapaOculto[x].length-1 && x > 0 && x < mapaOculto[x].length-1){ //última fila

                        contadorMina = 0;

                        for (int i = y-1; i<=y;i++ ){

                            for (int j = x-1; j<=x+1;j++){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    }

                    else if (y < mapaOculto[x].length-1 && x > 0 && y > 0 && x < mapaOculto[x].length-1){ //mapa en general

                        contadorMina = 0;

                        for (int i = y-1; i<=y+1;i++ ){

                            for (int j = x-1; j<=x+1;j++){

                                if (mapaOculto[i][j].equals("M")||mapaOculto[i][j].equals("10")){

                                    contadorMina +=1;

                                }
                            }
                        }

                        mapaOculto[y][x] = contadorMina+"";

                    } 
                }
            }
        }

        return mapaOculto;
    }  

    public static void imprimirMapa(String[][] mapa){

        int asterisco = 0;

        System.out.print(" "+"\t"); // Primera fila del mapa

        for (int i = 0; i<mapa.length;i++){

            System.out.print (i+1+"\t");

        }

        System.out.println();
        System.out.println();

        for (int y=0; y < mapa.length; y++) {

           System.out.print(y+1+"\t");

           for (int x = 0; x < mapa.length; x++){

               if (mapa[x][y].equals("M")){ //Mina

                   System.out.print("*");
                   asterisco += 1;

               }

               else if (mapa[x][y].equals("10")) { //Mina marcada

                   System.out.print("P");

               }

               else if (mapa[x][y].equals("1")||mapa[x][y].equals("2")||mapa[x][y].equals("3")||mapa[x][y].equals("4")||mapa[x][y].equals("5")||mapa[x][y].equals("6")||mapa[x][y].equals("7")||mapa[x][y].equals("8")) { //numero, comentar este bucle si quieres ver los numeros

                   System.out.print("*");
                   asterisco += 1;

               }

               else if (mapa[x][y].equals("11")||mapa[x][y].equals("21")||mapa[x][y].equals("22")||mapa[x][y].equals("23")||mapa[x][y].equals("24")||mapa[x][y].equals("25")||mapa[x][y].equals("26")||mapa[x][y].equals("27")||mapa[x][y].equals("28")) { //Espacio vacío marcado o numero

                   System.out.print("P");
                   asterisco += 1;

               }

               else if (mapa[x][y].equals("0")) { //Espacio vacío

                   System.out.print("*");
                   asterisco += 1;

               }

               else {

               System.out.print (mapa[x][y]);

               }

               if (y!=mapa[y].length){ 

                   System.out.print("\t");          
                }
            }

           System.out.println();
        }

        if (asterisco > minasFijo){
        System.out.println();
        System.out.println();
        menu();
        }
        else{

            System.out.println("¡Has ganado champion!");

        }            
    }

    public static void menu(){

        Scanner lector = new Scanner (System.in);

        int respuesta;

        System.out.println("1. Descubrir casilla.");
        System.out.println("2. Marcar casilla.");
        System.out.println("3. Desmarcar casilla.");
        
        while (!lector.hasNextInt()){
            System.out.println("Respuesta no válida...");
            lector.next();
        }

        respuesta = lector.nextInt();
        
        

        if (respuesta == 1){

            descubrirCasilla();

        }

        else if (respuesta == 2){

            marcarCasilla();

        }

        else if (respuesta == 3){

            desmarcarCasilla();

        }

        else{

            System.out.println("Respuesta no válida");
            menu();

        }

}

    public static void marcarCasilla(){

        Scanner lector = new Scanner (System.in);

        int x;
        int y;

        System.out.println("¿Qué casilla desea marcar?");

        while (!lector.hasNextInt()){
            System.out.println("Te he pillado Nacho, dame un número en el rango...");
            lector.next();
        }
        x = lector.nextInt()-1;
        while (!lector.hasNextInt()){
            System.out.println("Te he pillado Nacho, dame un número en el rango...");
            lector.next();
        }
        y = lector.nextInt()-1;

        if (x<0||y<0||x >= mapa.length || y >= mapa.length){

            System.out.println("Fuera de rango");
            menu();

        }

        if (mapa[x][y].equals("10")||mapa[x][y].equals("11")||mapa[x][y].equals("21")||mapa[x][y].equals("22")||mapa[x][y].equals("23")||mapa[x][y].equals("24")||mapa[x][y].equals("25")||mapa[x][y].equals("26")||mapa[x][y].equals("27")||mapa[x][y].equals("28")){

            System.out.println("Casilla ya marcada");
            marcarCasilla();

        }

        else if (mapa[x][y].equals("M")){ //Mina marcada

            mapa[x][y] = "10";

        }

        else if (mapa[x][y].equals("1")){

            mapa[x][y] = "21";

        }
        else if (mapa[x][y].equals("2")){

            mapa[x][y] = "22";

        }

        else if (mapa[x][y].equals("3")){

            mapa[x][y] = "23";

        }

        else if (mapa[x][y].equals("4")){

            mapa[x][y] = "24";

        }

        else if (mapa[x][y].equals("5")){

            mapa[x][y] = "25";

        }

        else if (mapa[x][y].equals("6")){

            mapa[x][y] = "26";

        }

        else if (mapa[x][y].equals("7")){

            mapa[x][y] = "27";

        }

        else if (mapa[x][y].equals("8")){

            mapa[x][y] = "28";

        }


        else if (mapa[x][y].equals("0")){ //Espacio marcado

            mapa[x][y] = "11";

        }



        imprimirMapa(mapa);



}

    public static void desmarcarCasilla() {

        Scanner lector = new Scanner (System.in);

        int x;
        int y;

        System.out.println("¿Qué casilla desea desmarcar?");

        while (!lector.hasNextInt()){
                System.out.println("Te he pillado Nacho, dame un número en el rango...");
                lector.next();
            }

            x = lector.nextInt()-1;

        while (!lector.hasNextInt()){
            System.out.println("Te he pillado Nacho, dame un número en el rango...");
            lector.next();
        }

        y = lector.nextInt()-1;

        if (x<0||y<0||x > mapa.length || y > mapa.length){

            System.out.println("Fuera de rango");
            menu();

        }

        if (mapa[x][y].equals("11")){

            mapa[x][y] = "0";

        }

        else if (mapa[x][y].equals("10")){

            mapa[x][y] = "M";

        }

        else if (mapa[y][x].equals("21")){

            mapa[x][y] = "1";

        }

        else if (mapa[x][y].equals("22")){

            mapa[x][y] = "2";

        }

        else if (mapa[x][y].equals("23")){

            mapa[x][y] = "3";

        }

        else if (mapa[x][y].equals("24")){

            mapa[x][y] = "4";

        }

        else if (mapa[x][y].equals("25")){

            mapa[x][y] = "5";

        }

        else if (mapa[x][y].equals("26")){

            mapa[x][y] = "6";

        }

        else if (mapa[x][y].equals("27")){

            mapa[x][y] = "7";
        }

        else if (mapa[x][y].equals("28")){

            mapa[x][y] = "8";
        }

        else {

            System.out.println("Casilla no marcada");
            menu();
        }

        imprimirMapa(mapa);

    }

    public static void descubrirCasilla(){

        Scanner lector = new Scanner (System.in);

        int x;
        int y;

        System.out.println("¿Qué casilla desea descubrir?");
        
        while (!lector.hasNextInt()){
            System.out.println("Te he pillado Nacho, dame un número en el rango...");
            lector.next();
        }

        x = lector.nextInt()-1;
        
        while (!lector.hasNextInt()){
            System.out.println("Te he pillado Nacho, dame un número en el rango...");
            lector.next();
        }
        y = lector.nextInt()-1;
        
        if(x<0||y<0||x>mapa.length||y>mapa.length){
            
            System.out.println("Te has pasado de rango");
            imprimirMapa(mapa);
            return;
        }
        
        int pos[]={x,y};

        for (int i = 0; i<casillasDescubiertas.size();i++){

            int[] casilla = casillasDescubiertas.get(i);

            if (casilla[0] == x && casilla[1] == y){                

                System.out.println("Casilla ya descubierta");
                System.out.println("");
                imprimirMapa(mapa);
                return;

            }
        }
        if (mapa[x][y].equals("M")||mapa[x][y].equals("10")){

            System.out.println("Pum, has perdido");
            return;

        }
        else if (mapa[x][y].equals("11")){

        mapa[x][y]= "0";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("21")){

        mapa[x][y]= "1 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("22")){

        mapa[x][y]= "2 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("23")){

        mapa[x][y]= "3 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("24")){

        mapa[x][y]= "4 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("25")){

        mapa[x][y]= "5 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("26")){

        mapa[x][y]= "6 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("27")){

        mapa[x][y]= "7 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else if (mapa[x][y].equals("28")){

        mapa[x][y]= "8 ";
        quitarCasillas(x, y);
        imprimirMapa(mapa);
        }
        else {

            String numeroString = mapa[x][y];
            int numero = Integer.parseInt(numeroString.trim());

            if (esNumero(numero,x,y)){

                casillasDescubiertas.add(pos);
                mapa[x][y] = numero+" ";
                quitarCasillas(x,y);


            } else {

                casillasDescubiertas.add(pos);
                quitarCasillas(x,y);


            }
            imprimirMapa(mapa);
        }
}

    public static void quitarCasillas(int x, int y){

        if (estaFuera(x, y)){

            return;

        }

        if (mapa[x][y].equals("0")){

            int[][] casillas = Arround(x,y);

            for (int i = 0; i<8;i++){  

                if (casillas[i][0] <= -1 || casillas [i][1] >= mapa.length) {
                    continue;

                } else {

                int pos[]={x,y};
                casillasDescubiertas.add(pos);
                mapa[x][y] = "O";
                quitarCasillas(casillas[i][0], casillas[i][1]);

                }
            }
        }

        else if (!mapa[x][y].equals("0")||!mapa[x][y].equals("O")){

            mapa[x][y] = mapa[x][y] + " ";

        }
    }

    private static int[][] Arround(int x, int y) {

        int[] upLeft = {x-1,y-1},
        up = {x,y-1},
        upRight = {x+1,y-1},
        left = {x-1,y},
        right = {x+1,y},
        downLeft = {x-1,y+1},
        down = {x,y+1},
        downRight = {x+1,y+1};

        if (x == 0) {
            upLeft[0] = -1;
            left[0] = -1;
            downLeft[0] = -1;
        } else if (x == mapa[0].length-1) {
            upRight[0] = -1;
            right[0] = -1;
            downRight[0] = -1;
        }

        int[][] cells = {upLeft, up, upRight,
                left, right,
                downLeft, down, downRight};

        return cells;
}

    public static boolean esNumero (int numero, int x, int y){

    if (numero == 0) return false;

    return mapa[x][y].equals(""+numero)||mapa[x][y].equals("2"+numero);
    }

    public static boolean estaFuera(int x, int y) {
        return x < 0 || y < 0 || x >= mapa[0].length || y > mapa[0].length;
    }
}
 
    