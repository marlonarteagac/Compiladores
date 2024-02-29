package lexico_compi;

import java.util.Scanner;

public class javaboris {

    public static String texto;
    public static int [] vt = new int [10];
    public static int [] vtorden = new int [10];
    public static void main(String[] args) {
        System.out.println ("Por favor introduzca una cadena por teclado:");
        String texto;
        Scanner entradaEscaner = new Scanner (System.in); 
        texto = entradaEscaner.nextLine (); //Invoca
       
        System.out.println("texto digitado " +texto);
        int l = texto.length();
        int j = 0;
        int k = 0;
        int i;
        char c;
        for ( i=0; i < l; i++){
            c = texto.charAt(i);
            if ((c == '$')||(c == '#')||(c == '!')||(c == '+')||(c == '-')||(c == '*')||(c == '/')){
                vt[j]=i;
                j=j+1;
            }
        }
        l=j; // guardo la cantidad de poiciones usadas en el arreglo vt
        System.out.println ("arreglo de operadores y tipos de variables");
        for (i=0; i < l; i++){
            System.out.println ("posici[on "+i+" valor "+vt[i]);  
        }

        //ORDENAR 
        System.out.println ("ahora a ordenar");  
        j=0;
        k=1;
        for (i=0; i < l; i++){
            c = texto.charAt(vt[i]);
             System.out.println ("VALOR A GUARDAR "+c);  
            if ((c == '$')||(c == '#')||(c == '!')){
                vtorden[j]=vt[i];
                j=j+2;
            }else {
               vtorden[k]=vt[i];
                k=k+2; 
            }
        }
        System.out.println ("arreglo ORDENADO de operadores y tipos de variables");
        for (i=0; i < l; i++){
            System.out.println ("posici[on "+i+" valor "+vtorden[i]);  
        }
    }
    }
   

