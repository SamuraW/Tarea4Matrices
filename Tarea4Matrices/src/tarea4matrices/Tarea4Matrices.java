package tarea4matrices;

import java.util.Random;
import java.util.Scanner;

public class Tarea4Matrices {

    static Scanner entrada = new Scanner(System.in);
    static Scanner entrada2 = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int op = 1;
        while (op != 3) {
            System.out.println("Bienvenido a Tarea #4 Mtrices");
            System.out.println("Ingrese una opcion");
            System.out.println("1) BattleShi[p (Battle Naval)");
            System.out.println("2) Buscar Minas");
            op = entrada.nextInt();
            switch (op) {
                case 1: {
                    System.out.println("Bienvenido al BattleShip");
                    System.out.println("Esto es ----------------------YO NO LE TENGO MIEDO A NADA---------------------------------------");
                    BattleShip();
                    continue;

                }
                case 2: {
                    System.out.println("Yo no le tengo miedo a nada, pero soy precavido ");
                    BuscaMinas();
                    continue;
                }

                default: {
                    System.out.println("Ingrse una opcion valida para iniicar");
                    break;
                }

            }

        }
    }

    public static void BattleShip() {
        Scanner entrada = new Scanner(System.in);
        boolean ContinuarBattleShip = true;
        while (ContinuarBattleShip) {
            int bandera, banderaMenos;
            String matrix1[][], move;
            int coordenadas[], fila, columna, centinela1, centinela2;
            String matrix2[][], tama = "6,5";
            System.out.println("BATTLE SHIP");
            System.out.println("Modo de juego ");
            System.out.println("1) Un jugador ");
            System.out.println("2) Dos jugadores");
            System.out.print("Que modalidad desea jugar:");
            int op = entrada.nextInt();
            switch (op) {
                case 1: {
                    System.out.println("Ingrese a la opcion ---------1 JUGADOR-----------------");
                    System.out.println("\n");
                    bandera = 8;
                    banderaMenos = 1;
                    matrix1 = CrearMat(tama);
                    matrix1 = LlenarMatriz1(matrix1);
                    ImprimiendoMatriz(matrix1);
                    System.out.println("Ingrese el movimiento qque desea [fila] y [Columna] ");
                    move = entrada2.nextLine();
                    coordenadas = ValPosi(matrix1, move, bandera, banderaMenos);
                    fila = coordenadas[0];
                    columna = coordenadas[1];
                    bandera = modificandoBandera(fila, columna, matrix1, banderaMenos, bandera);
                    matrix1 = modificandoMatriz(matrix1, fila, columna);
                    while (bandera > 5) {
                        System.out.print("Ingrese su movimiento[fila,columna]:");
                        move = entrada2.nextLine();
                        coordenadas = ValPosi(matrix1, move, bandera, banderaMenos);
                        fila = coordenadas[0];
                        columna = coordenadas[1];
                        bandera = modificandoBandera(fila, columna, matrix1, banderaMenos, bandera);
                        matrix1 = modificandoMatriz(matrix1, fila, columna);
                    }
                    ImprimiendoMatriz(matrix1);
                    System.out.println("-------------------YOU WON----------------------------");
                    break;
                }
                case 2: {
                    System.out.println("BIENVENIDO AL---------------------------MULTIPLAYER--------------------- (******EMPZEMOS******)");
                    centinela1 = 8;
                    centinela2 = 8;
                    banderaMenos = 1;
                    matrix1 = CrearMat(tama);
                    matrix1 = LlenarMatriz1(matrix1);
                    matrix2 = CrearMat(tama);
                    matrix2 = LlenarMatriz2(matrix2);
                    while (centinela1 != 5 || centinela2 != 5) {
                        System.out.println("---------------OPONENTE----------------");
                        ImprimiendoMatriz(matrix2);
                        System.out.println("--------------JUGADOR 1-------------");
                        System.out.println("Ingrese su MOVIMIENTO ESTRATEGICO[FILA] Y [Columna]");
                        String movement1 = entrada2.nextLine();
                        coordenadas = ValPosi(matrix2, movement1, centinela2, banderaMenos);
                        fila = coordenadas[0];
                        columna = coordenadas[1];
                        centinela2 = modificandoBandera(fila, columna, matrix2, banderaMenos, centinela2);
                        matrix2 = modificandoMatriz(matrix2, fila, columna);
                        System.out.println("");
                        System.out.println("OPONENTE A LA VISTA");
                        ImprimiendoMatriz(matrix1);
                        System.out.println("Su TURNO -------------------------JUGADOR 2-------------------------");
                        System.out.println("INGRESE SU MOVIMIENTO ESTRATEGIO[FILA][COLUMNA]");
                        String move2 = entrada2.nextLine();
                        int[] coordenadas2 = ValPosi(matrix1, move2, centinela1, banderaMenos);
                        int filas2 = coordenadas2[0];
                        int columna2 = coordenadas2[1];
                        centinela1 = modificandoBandera(filas2, columna2, matrix1, banderaMenos, centinela1);
                        matrix1 = modificandoMatriz(matrix1, filas2, columna2);
                        System.out.println("");
                    }
                    if (centinela1 == 5) {
                        System.out.println("------------Felicidades A--------------");
                        System.out.println("-------------Jugador1----------------");
                    }
                    if (centinela2 == 5) {
                        System.out.println("------------Felicidades A--------------");
                        System.out.println("-------------Jugador----------------");
                    }
                    break;
                }

            }
            System.out.println("Quiere volver al menu principal o al menu del Battleship? [ 1) Menu principal / 2) Menu del Battleship ] ");
            int contibattleship = entrada.nextInt();
            //Solo hay una condicion porque solo aqui se hace false el seguir en el battle, entonces sigue true y sigue en la condicion [play] o jugando
            if (contibattleship == 1) {
                ContinuarBattleShip = false;
                System.out.println("Volvio al Menu principal");
            }

        }
    }

    public static void ImprimiendoMatriz(String[][] Matriz1) {
        for (int i = 0; i < Matriz1.length; i++) {
            for (int j = 0; j < (Matriz1[i]).length; j++) {
                if (Matriz1[i][j].equals("*")) {
                    System.out.print("[ ] ");
                } else {
                    System.out.print("[" + Matriz1[i][j] + "] ");
                }
            }
            System.out.println("");
        }
    }

    public static String[][] CrearMat(String tama1) {
        String tamano1 = tama1;
        String[] cords = tamano1.split(",");
        int filas = Integer.parseInt(cords[0]);
        int columnas = Integer.parseInt(cords[1]);
        return new String[filas][columnas];
    }

    public static String[][] LlenarMatriz1(String[][] Matriz1) {
        for (int i = 0; i < Matriz1.length; i++) {
            for (int j = 0; j < (Matriz1[i]).length; j++) {
                if ((i < 3 && j == 0) || (i > 2 && j == 4) || (i == 1 && j == 2) || (i == 1 && j == 3)) {
                    Matriz1[i][j] = "*";
                } else {
                    Matriz1[i][j] = " ";
                }
            }
        }
        return Matriz1;
    }

    public static String[][] LlenarMatriz2(String[][] Matriz2) {
        for (int i = 0; i < Matriz2.length; i++) {
            for (int j = 0; j < (Matriz2[i]).length; j++) {
                if ((i > 0 && j == 3 && i < 4 && j == 3) || (i > 2 && j == 1) || (i == 5 && j > 2)) {
                    Matriz2[i][j] = "*";
                } else {
                    Matriz2[i][j] = " ";
                }
            }
        }
        return Matriz2;
    }

    public static int[] ValPosi(String[][] recibiendoMatriz, String move, int bandera, int banderamenos) {
        Scanner entrada = new Scanner(System.in);
        int[] coordenadas = new int[2];
        boolean valida = false;
        while (!valida) {
            String[] MoviEU1 = move.split(",");
            int filamov1 = Integer.parseInt(MoviEU1[0]);
            int columnamov1 = Integer.parseInt(MoviEU1[1]);
            if (filamov1 >= 1 && filamov1 <= recibiendoMatriz.length && columnamov1 >= 1 && columnamov1 <= (recibiendoMatriz[0]).length) {
                if (recibiendoMatriz[filamov1 - 1][columnamov1 - 1].equals(" ")) {
                    coordenadas[0] = filamov1 - 1;
                    coordenadas[1] = columnamov1 - 1;
                    valida = true;
                    continue;
                }
                if (recibiendoMatriz[filamov1 - 1][columnamov1 - 1].equals("*")) {
                    bandera = modificandoBandera(filamov1 - 1, columnamov1 - 1, recibiendoMatriz, bandera, banderamenos);
                    coordenadas[0] = filamov1 - 1;
                    coordenadas[1] = columnamov1 - 1;
                    valida = true;
                    continue;
                }
                System.out.print("Ya atacaste esa posicion, ingresa otra coordenada[fila,columna]:");
                move = entrada.nextLine();
                System.out.println("");
                continue;
            }
            System.out.print("Tus coordenadas no se encuentran en el terreno atacado, ingrese otra coordenada[fila,columna]:");
            move = entrada.nextLine();
            System.out.println("");
        }
        return coordenadas;
    }

    public static int modificandoBandera(int filas, int columnas, String[][] mtz, int bandemenos, int bandera) {
        String[][] Matriz1 = mtz;
        int band = bandera;
        int bandeMenos = bandemenos;
        if (Matriz1[filas][columnas].equals("*")) {
            band -= bandeMenos;
        }
        return band;
    }

    public static String[][] modificandoMatriz(String[][] Matriz1, int filas, int columnas) {
        if (Matriz1[filas][columnas].equals("*")) {
            Matriz1[filas][columnas] = "X";
            ImprimiendoMatriz(Matriz1);
            System.out.println("--------barco ha sido estropeado------------");
        } else if (Matriz1[filas][columnas].equals(" ")) {
            Matriz1[filas][columnas] = "X";
            ImprimiendoMatriz(Matriz1);
            System.out.println("---------------Bomba al agua--------------");
        }
        return Matriz1;
    }

    public static void BuscaMinas() {
        boolean continuargame2 = true;
        while (continuargame2 == true) {
            System.out.println("---------------------Busca Minas------------------- ");
            int fila = 6;
            int columna = 5;
            String[][] matrix3 = CrearMatrix2(fila, columna);
            matrix3 = LlenarMatrix2(matrix3);
            ImprimiendoMatriz(matrix3);
            boolean findeljuego = false;
            while (!findeljuego) {
                System.out.print("Ingrese su movimiento[fila] y [Columna] EJEMPLO: (2,3):");
                String movimiento = entrada2.nextLine();
                int[] coordenadas = ValidandoPosi2(matrix3, movimiento);
                int filas3 = coordenadas[0];
                int columnas3 = coordenadas[1];
                matrix3 = ataques(matrix3, filas3, columnas3);
                findeljuego = finaldeJuego(matrix3, findeljuego);
                ImprimiendoMatriz(matrix3);
            }
            System.out.print("Desea salir del buscaminas? 1. si 2. No ");
            int continuar2 = entrada.nextInt();
            if (continuar2 == 1) {
                continuargame2 = false;
            }
        }
    }

    public static String[][] CrearMatrix2(int filas, int columnas) {
        int posifila = filas;
        int posicolumna = columnas;
        return new String[posifila][posicolumna];
    }

    //Método para llenar la matriz del busca minas 
    public static String[][] LlenarMatrix2(String[][] matrix) {
        Random ram = new Random();
        int filas = matrix.length;
        int columnas = (matrix[0]).length;
        int i;
        for (i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrix[i][j] = " ";
            }
        }
        for (i = 0; i < 5; i++) {
            int filaAleatoria = ram.nextInt(filas);
            int columnaAleatoria = ram.nextInt(columnas);
            while (matrix[filaAleatoria][columnaAleatoria].equals("*")) {
                filaAleatoria = ram.nextInt(filas);
                columnaAleatoria = ram.nextInt(columnas);
            }
            matrix[filaAleatoria][columnaAleatoria] = "*";
        }
        return matrix;
    }

    public static int[] ValidandoPosi2(String[][] matriz, String movimientop3) {
        String[][] matriz3 = matriz;
        int[] coordenadas = new int[2];
        String mov = movimientop3;
        boolean valida = false;
        while (!valida) {
            String[] MoviEU1 = mov.split(",");
            int filaMov3 = Integer.parseInt(MoviEU1[0]) - 1;
            int columnaMov3 = Integer.parseInt(MoviEU1[1]) - 1;
            if ((filaMov3 >= 0 && filaMov3 < matriz3.length) || (columnaMov3 >= 0 && columnaMov3 < (matriz3[0]).length)) {
                if (matriz3[filaMov3][columnaMov3].equals(" ") || matriz3[filaMov3][columnaMov3].equals("*")) {
                    coordenadas[0] = filaMov3;
                    coordenadas[1] = columnaMov3;
                    valida = true;
                    continue;
                }
                System.out.print("Su ataque ya fue realizado en esa posicion, ingresa otra coordenada[fila,columna]: ");
                mov = entrada2.nextLine();
                System.out.println("");
                continue;
            }
            System.out.print("Las cordenadas no se encuentran en el terreno atacado, ingrese otra coordenada[fila,columna]: ");
            mov = entrada2.nextLine();
            System.out.println("");
        }
        return coordenadas;
    }

    public static String[][] ataques(String[][] Matriz1, int filas, int columnas) {
        if (Matriz1[filas][columnas].equals("*")) {
            Matriz1[filas][columnas] = "X";
            System.out.println("--------------Encontro una bomba-------------");
            System.out.println("");
        } else if (Matriz1[filas][columnas].equals(" ")) {
            Matriz1[filas][columnas] = "X";
            System.out.println("no es una bomba");
            System.out.println("");
        }
        return Matriz1;
    }

    public static boolean finaldeJuego(String[][] matrix, boolean finjuego) {
        boolean fin = finjuego;
        String[][] matriz3 = matrix;
        int cont = 0;
        int i;
        for (i = 0; i < matriz3.length; i++) {
            for (int j = 0; j < (matriz3[0]).length; j++) {
                if (matriz3[i][j].equals("*")) {
                    cont++;
                }
            }
        }

        if (cont < 5) {
            for (i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (matriz3[i][j].equals("X")) {
                        fin = true;
                        return fin;
                    }
                }
            }
        }
        return fin;
    }
}
