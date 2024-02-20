package lexico_compi;

import java.util.Scanner;

public class AnalizadorLexico {
//agregue el comentario para actualizar en git
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un texto (máximo 20 caracteres): ");
        String texto = scanner.nextLine();
        String resultado = "";

        if (texto.isEmpty()) {
            resultado = "Por favor, ingrese un texto.";
        } else {
            char[] arreglo_texto = texto.toCharArray();
            int[] identificadores = new int[arreglo_texto.length]; // Tamaño del array de identificadores

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
                System.out.println("La variable no puede estar vacia try catch");
                
            }
            // Obtener posiciones de los identificadores
          

            //con este while verifico los caracteres para decir si es valido o no
            int k = 0;
            while (k < arreglo_texto.length) {
                char caracter_actual = arreglo_texto[k];
                char caracter_siguiente = (k + 1 < arreglo_texto.length) ? arreglo_texto[k + 1] : ' ';


               System.out.println("k siguiente es :" + caracter_siguiente);

                if (esOperador(caracter_actual) && esLetra(caracter_siguiente)) {
                    System.out.println("no se puede letra despues de operador, flata identificador de variable");
          
                }else if (esidentificador(caracter_siguiente) && esLetra(caracter_siguiente)) {
                    System.out.println("letra despues de identificador");
                    break;
                }
 
                k++;
            }

            // Validar las variables
            for (int i = contador - 1; i > 0; i--) {
                char identificadorAnterior = arreglo_texto[identificadores[i - 1]];
                if (identificadorAnterior == '+' || identificadorAnterior == '-'
                        || identificadorAnterior == '/' || identificadorAnterior == '*') {
                    continue;
                }

                int finVar = identificadores[i];
                int inicioVar = identificadores[i - 1] + 1;
                int longitudVariable = finVar - inicioVar;

                char[] variable = new char[longitudVariable];
                String mv = "";

                for (int j = inicioVar; j < finVar; j++) {
                    variable[j - inicioVar] = arreglo_texto[j];
                    mv += arreglo_texto[j];

                }

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
                    resultado += " - válida " + mv + "\n";
                }

            }
        }
        System.out.println("" + resultado);
    }

    public static boolean validarvariable(char[] caracteres) {

        // Regla adicional: La variable no puede estar vacía
        if (caracteres.length == 0) {
            System.out.println("La variable no puede estar vacía");
            return false;
        }

        for (int i = 0; i < caracteres.length; i++) {
            char c = caracteres[i];

            // Regla 1: No puede empezar por un valor numérico
            if (i == 0 && (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9')) {
                System.out.println("No puede empezar por un valor numérico la variable");
                return false;
            }

            // Regla 2: Debe empezar por una letra
            if (i == 0 && !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                System.out.println("Debe empezar por una letra la variable");
                return false;
            }

            // Regla 3: Los caracteres pueden ser letras, dígitos, guiones bajos o operadores
            if (!(Character.isLetterOrDigit(c) || c == '_' || c == '+' || c == '-' || c == '*')) {
                System.out.println("Los caracteres pueden ser letras, dígitos, guiones bajos o operadores y no puede tener espacio");
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

    private static boolean esidentificador(char c) {
        return c == '$' || c == '#' || c == '!';
    }
}
