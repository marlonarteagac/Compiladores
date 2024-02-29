package lexico_compi;

import java.util.Arrays;
import java.util.Scanner;

public class AnalizadorLexico {
    // agregue el comentario para actualizar en git
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // pepitoperez
        System.out.println("Ingrese un texto (máximo 20 caracteres): ");
        String texto = scanner.nextLine();
        String resultado = "";
        int var_cont = 0;
        int bandera = 0;

        if (texto.isEmpty()) {
            resultado = "Por favor, ingrese un texto.";
        } else {
            char[] arreglo_texto = texto.toCharArray();
            int[] identificadores = new int[arreglo_texto.length]; // Tamaño del array de identificadores
            int l = arreglo_texto.length;
            String[] operadoresEncontrados = new String[arreglo_texto.length];
            int contadorOperadores = 0;

            for (int i = 0; i < arreglo_texto.length; i++) {
                switch (arreglo_texto[i]) {
                    case '+':
                    case '-':
                    case '*':
                    case '/':
                        operadoresEncontrados[i] = String.valueOf(arreglo_texto[i]);
                        contadorOperadores++;
                        // System.out.println("operadores encontrados" + arreglo_texto[i] );

                }

            }

            // Contador para llevar el control de la cantidad de identificadores encontrados
            int contador = 0;
            try {
                for (int i = 0; i < arreglo_texto.length; i++) {
                    switch (arreglo_texto[i]) {
                        case '$':
                        case '#':
                        case '!':
                        case '+':
                        case '-':
                        case '*':
                        case '/':
                            identificadores[contador] = i;
                            contador++;
                            break;
                    }

                    if (arreglo_texto.length - 1 == i) {
                        identificadores[contador] = i + 1;
                        contador++;
                    }
                }

            } catch (Exception e) {
                bandera = 1;
                Mensaje(bandera);
                System.out.println("La variable no puede estar vacia try catch");

            }

            // con este while verifico los caracteres para decir si es valido o no

            int k = 0;
            while (k < arreglo_texto.length) {
                char caracter_actual = arreglo_texto[k];
                char caracter_siguiente = (k + 1 < arreglo_texto.length) ? arreglo_texto[k + 1] : ' ';
                // char caracter3 = (k + 2 < arreglo_texto.length) ? arreglo_texto[k + 2] : ' ';

                if (esidentificador(caracter_actual) && esLetra(caracter_siguiente)) {
                    // System.out.println("paso 1");
                    k++;

                } else if (esidentificador(caracter_actual)) {
                    if (esNumero(caracter_siguiente)) {
                        bandera = 1;
                        break;
                    }
                }

                else if (esOperador(caracter_actual)) {
                    if (esLetra(caracter_siguiente)) {
                        bandera = 1;
                        break;
                    }
                } else if (esidentificador(caracter_actual)) {
                    if (esidentificador(caracter_siguiente)) {
                        bandera = 1;
                        break;
                    }
                }

                k++;

                System.out.println(caracter_siguiente);
            }

            System.out.println("la bander vale: " + bandera);
            if (bandera <= 0) {
                bandera = 0;
                Mensaje(bandera);
            } else {
                bandera = 1;
                Mensaje(bandera);
            }
            // Validar las variables

            // ARREGLO PARA GUARDAR LAS VARIABLES COMPLETAS}

            String[] arregloVariables = new String[contador];

            for (int i = contador - 1; i > 0; i--) {// INICIO DEL FOR PRINCIPAL
                char identificadorAnterior = arreglo_texto[identificadores[i - 1]];
                if (identificadorAnterior == '+' || identificadorAnterior == '-'
                        || identificadorAnterior == '/' || identificadorAnterior == '*') {
                    continue;

                }
//SACAR LAS VARIABLES DESPUES DEL IDENTIFICADOR
                int finVar = identificadores[i];
                int inicioVar = identificadores[i - 1] + 1;
                int longitudVariable = finVar - inicioVar;

                char[] variable = new char[longitudVariable];
                String mv = "";

                for (int j = inicioVar; j < finVar; j++) {
                    variable[j - inicioVar] = arreglo_texto[j];
                    mv += arreglo_texto[j];

                }

                // // PARA GUARDAR LA VARIABLE COMPLETA EN EL ARREGLO PARA ORDENAR CON EL IDENTIFICADOR
                int a_finVar = identificadores[i];
                int a_inicioVar = identificadores[i - 1];
                int a_longitudVariable = a_finVar - a_inicioVar;

                char[] a_variable = new char[a_longitudVariable];

                // LA VARIABLE QUE TENDRA EL IDENTIFICADOR
                String a_mv = "";

                for (int j = a_inicioVar; j < a_finVar; j++) {
                    a_variable[j - a_inicioVar] = arreglo_texto[j];
                    a_mv += arreglo_texto[j];

                    arregloVariables[i - 1] = a_mv;

                }

                // AQUI ME ESTA IMPRIMIENDO BIEN LAS VARIABLES CADA QUE DA LA VUELTA EL CICLO

                if (identificadorAnterior == '$') {
                    resultado += "Variable tipo string: ";
                } else if (identificadorAnterior == '#') {
                    resultado += "Variable tipo numérica: ";
                } else if (identificadorAnterior == '!') {
                    resultado += "Variable tipo booleana: ";
                }

                if (!validarvariable(variable)) {
                    resultado += " - no válida\n";
                } else {
                    resultado += "variable: " + mv + "\n";
                }

                var_cont += 1;

                // HASTA AQUI LLEGA LA CUESTION DE LAS VARIABLES

            } // FIN DEL FOR PRINCIPAL

            // IMPRIMIR LAS VARIABLES COMPLETAS
            for (int p = 0; p < arregloVariables.length; p++) {
                if (arregloVariables[p] != null) {
                    System.out.println("Variable " + (p + 1) + ": " + arregloVariables[p]);
                }

            }
            System.out.println("contador operadores: " + contadorOperadores);
            System.out.println("contador variables: " + var_cont);

            System.out.println("ahora a ordenar");
            // UNIR LOS ARREGLOS EN UNO SOLO ARREGLO PARA ORDENARLOS
            String[] nuevoArreglo = unirArreglos(eliminarNull(arregloVariables), eliminarNull(operadoresEncontrados));
            System.out.println("arreglo nuevoArreglo"+Arrays.toString(nuevoArreglo));
            for (int j = 0; j < nuevoArreglo.length; j++) {

            }

            String[] ordenado = new String[nuevoArreglo.length];
            int j = 0;
            int n = 1;
            for (int i = 0; i < nuevoArreglo.length; i++) {

                String c = (nuevoArreglo[i]);
                System.out.println("VALOR A GUARDAR " + c);
                if ((String.valueOf(c).equals("+")) || (String.valueOf(c).equals("-"))  || (String.valueOf(c).equals("*"))|| (String.valueOf(c).equals("/"))  ) {
                    ordenado[n] = nuevoArreglo[i];
                    n = n + 2;
                } else {
                   

                    ordenado[j] = nuevoArreglo[i];
                    j = j + 2;
                }

                // System.out.println("----------------"+nuevoArreglo[j]+"----------------"+ordenado[i]+"----------------");
            }

            System.out.println(Arrays.toString(ordenado));

            System.out.println("arreglo ORDENADO de operadores y tipos de variables");

            for (int h=0; h < nuevoArreglo.length ; h++){
            System.out.println ("posición "+h+" valor "+ordenado[h]);

            }

        }

    }

    public static String[] eliminarNull(String[] arreglo) {

        int nuevoTamanio = 0;
        for (String valor : arreglo) {
            if (valor != null) {
                nuevoTamanio++;
            }
        }

        String[] nuevoArreglo = new String[nuevoTamanio];
        int j = 0;
        for (String valor : arreglo) {
            if (valor != null) {
                nuevoArreglo[j++] = valor;
            }
        }
        return nuevoArreglo;
    }

    public static String[] unirArreglos(String[] arreglo1, String[] arreglo2) {
        int longitudTotal = (arreglo1.length + arreglo2.length);
        String[] nuevoArreglo = new String[longitudTotal];
        int indiceNuevoArreglo = 0;

        // Recorrer ambos arreglos y agregar elementos al nuevo arreglo
        for (String elemento : arreglo1) {
            if (elemento != null) {
                nuevoArreglo[indiceNuevoArreglo++] = elemento;
            }
        }
        for (String elemento : arreglo2) {
            if (elemento != null) {
                nuevoArreglo[indiceNuevoArreglo++] = elemento;
            }
        }

        return nuevoArreglo;
    }

    // System.out.println("" + resultado + "");
    // public static void organizarArreglo(String[] variables, char[] operadores,
    // String[] nuevoArreglo) {
    // int indiceVariable = 0;
    // int indiceOperador = 1;
    // int indiceNuevoArreglo = 0;

    // // Recorrer los arreglos intercalando variables y operadores
    // while (indiceVariable < variables.length && indiceOperador <
    // operadores.length) {
    // nuevoArreglo[indiceNuevoArreglo++] = variables[indiceVariable++];
    // nuevoArreglo[indiceNuevoArreglo++] =
    // String.valueOf(operadores[indiceOperador++]);
    // }

    // // Agregar las variables restantes al nuevo arreglo
    // while (indiceVariable < variables.length) {
    // nuevoArreglo[indiceNuevoArreglo++] = variables[indiceVariable++];
    // }

    // // Agregar los operadores restantes al nuevo arreglo
    // while (indiceOperador < operadores.length) {
    // nuevoArreglo[indiceNuevoArreglo++] =
    // String.valueOf(operadores[indiceOperador++]);
    // }
    // }

    public static boolean validarvariable(char[] caracteres) {

        // Regla adicional: La variable no puede estar vacía
        if (caracteres.length == 0) {
            return false;
        }

        for (int i = 0; i < caracteres.length; i++) {
            char c = caracteres[i];

            // Regla 1: No puede empezar por un valor numérico
            if (i == 0 && (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7'
                    || c == '8' || c == '9')) {
                System.out.println("No puede empezar por un valor numérico la variable");
                return false;
            }

            // Regla 2: Debe empezar por una letra
            if (i == 0 && !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_')) {
                System.out.println("Debe empezar por una letra la variable");
                return false;
            }

            // Regla 3: Los caracteres pueden ser letras, dígitos, guiones bajos o
            // operadores
            if (!(Character.isLetterOrDigit(c) || c == '_' || c == '+' || c == '-' || c == '*')) {
                System.out.println(
                        "Los caracteres pueden ser letras, dígitos, guiones bajos o operadores y no puede tener espacio");
                return false;
            }

        }
        return true;
    }

    private static boolean esLetra(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean esNumero(char c) {
        return (c >= '0' && c <= '9');
    }

    private static boolean esidentificador(char c) {
        return c == '$' || c == '#' || c == '!';
    }

    public static void Mensaje(int bandera) {
        if (bandera == 0) {
            System.out.println("EL TEXTO ES VALIDO");

        } else {
            System.out.println("EL TEXTO NO ES VALIDO");

        }
    }

}
