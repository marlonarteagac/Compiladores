/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package lexico_compi;

import java.util.Arrays;
import java.util.Scanner;
// import static lexico_compi.AnalizadorLexico.Mensaje;
// import static lexico_compi.AnalizadorLexico.eliminarNull;
// import static lexico_compi.AnalizadorLexico.unirArreglos;
// import static lexico_compi.AnalizadorLexico.validarLongitudTexto;
import static lexico_compi.AnalizadorLexico.validarvariable;

/**
 *
 * @author marlon
 */
public class vista extends javax.swing.JFrame {
    int lexico;

    int sintactico;
    int semantico;

    public vista() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_limpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_mensaje = new javax.swing.JTextArea();
        txt_texto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_limpiar.setText("Limpiar");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        jLabel1.setText("Ingresar texto");

        txt_mensaje.setColumns(20);
        txt_mensaje.setRows(5);
        jScrollPane1.setViewportView(txt_mensaje);

        txt_texto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_textoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout
                                                .createSequentialGroup()
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane1,
                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, 439,
                                                                Short.MAX_VALUE)
                                                        .addComponent(txt_texto,
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(btn_limpiar))
                                                .addGap(20, 20, 20)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_texto, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_limpiar)
                                .addGap(17, 17, 17)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btn_limpiarActionPerformed
        Limpiar();
        // REINICIO LEXICO A 0 PARA QUE NO HAYA ERROR
        lexico = 0;
        sintactico = 0;
        semantico = 0;
    }// GEN-LAST:event_btn_limpiarActionPerformed

    private void txt_textoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txt_textoActionPerformed
        Scanner scanner = new Scanner(System.in);
        // pepitoperez
        System.out.println("Ingrese un texto (máximo 20 caracteres): ");
        String texto = txt_texto.getText();
        String resultado = "";
        int var_cont = 0;
        int bandera = 0;

        // Verificamos inicialmente que el texto cumpla con la longitud minima
        if (texto.length() < 20) {
            txt_mensaje.append("" + validarLongitudTexto(texto) + "\n");
            if (texto.isEmpty()) {
                resultado = "Por favor, ingrese un texto.";

            } else {
                char[] arreglo_texto = texto.toCharArray();
                int[] identificadores = new int[arreglo_texto.length]; // Tamaño del array de identificadores
                // int l = arreglo_texto.length;
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
                    txt_mensaje.append("La variable no puede estar vacia try catch\n");

                }

                // con este while verifico los caracteres para decir si es valido o no
                int k = 0;
                while (k < arreglo_texto.length) {
                    char caracter_actual = arreglo_texto[k];
                    char caracter_siguiente = (k + 1 < arreglo_texto.length) ? arreglo_texto[k + 1] : ' ';
                    // char caracter3 = (k + 2 < arreglo_texto.length) ? arreglo_texto[k + 2] : ' ';

                    if (esidentificador(caracter_actual) && esLetra(caracter_siguiente)) {

                        k++;

                    }
                    if (!esidentificador(caracter_actual)
                            && !esLetra(caracter_actual)
                            && !esNumero(caracter_actual)
                            && !esOperador(caracter_actual)) {

                        bandera = 1;
                        break;

                    }
                    if (k <= 1) {

                        if (esNumero(caracter_actual) || esNumero(caracter_siguiente)) {
                            bandera = 1;
                            break;
                        }
                    }
                    if (k <= 1) {

                        if (esLetra(caracter_actual)) {
                            bandera = 1;
                            break;
                        }
                    }

                    if (esidentificador(caracter_actual)) {
                        if (esidentificador(caracter_siguiente)) {
                            bandera = 1;
                            break;
                        }
                    }
                    if (esidentificador(caracter_actual)) {
                        if (esOperador(caracter_siguiente)) {
                            bandera = 1;
                            break;
                        }
                    }
                    if (esidentificador(caracter_actual)) {
                        if (esNumero(caracter_siguiente)) {
                            bandera = 1;
                            break;
                        }
                    }

                    if (esOperador(caracter_actual)) {
                        if (esLetra(caracter_siguiente)) {
                            bandera = 1;
                            break;
                        }

                    }

                    k++;

                }

                txt_mensaje.append(
                        "----------------------------------------LEXICO--------------------------------------------\n ");
                // DESPUES DE LAS REGLAS EJECUTO SI LA BANDERA TIENE ALGUN VALOR <=0 PARA
                // DETERMINAR SI CUMPLE
                // O NO CUMPLE CON LAS REGLAS.
                if (bandera <= 0) {
                    bandera = 0;
                    Mensaje(bandera);
                    // A LEXICO LE CAMBIO EL VALOR GLOBAL SI SE CUMPLEN LAS REGLAS
                    lexico = 1;

                } else if (bandera > 0) {
                    // CASO DADO QUE LA BANDERA SEA 1 Y SE ACTIVE EL BREAK, SE TERMINA HASTA DONDE
                    // IBA LA VALIDACION
                    // Y MARCA QUE EL TEXTO NO ES VALIDO
                    bandera = 1;
                    Mensaje(bandera);

                }
                // SI LAS REGLAS SE CUMPLE, LEXICO VALE 1, Y EJECUTARÁ TODO LO QUE ESTÁ EN EL
                // CONDICIONAL, CASO CONTRARIO MUESTRA EL MENSJAE DEL METODO TEXTO NO VALIDO Y
                // NO
                // MOSTRARA NADA MÁS
                if (lexico >= 1) {
                    // ARREGLO PARA GUARDAR LAS VARIABLES COMPLETAS}
                    String[] arregloVariables = new String[contador];

                    for (int i = contador - 1; i > 0; i--) {// INICIO DEL FOR PRINCIPAL
                        char identificadorAnterior = arreglo_texto[identificadores[i - 1]];
                        if (identificadorAnterior == '+' || identificadorAnterior == '-'
                                || identificadorAnterior == '/' || identificadorAnterior == '*') {
                            continue;

                        }
                        // SACAR LAS VARIABLES DESPUES DEL IDENTIFICADOR
                        int finVar = identificadores[i];
                        int inicioVar = identificadores[i - 1] + 1;
                        int longitudVariable = finVar - inicioVar;

                        char[] variable = new char[longitudVariable];
                        String mv = "";

                        for (int j = inicioVar; j < finVar; j++) {
                            variable[j - inicioVar] = arreglo_texto[j];
                            mv += arreglo_texto[j];

                        }
                        if (variable.length <= 5) {

                        } else {
                            System.out.println("Nombre de la variable excede los caracteres (5)");
                            bandera = 1;
                            Mensaje(bandera);

                            break;
                        }
                        // System.out.println("variable: " + mv);
                        // // PARA GUARDAR LA VARIABLE COMPLETA EN EL ARREGLO PARA ORDENAR CON EL
                        // IDENTIFICADOR
                        int a_finVar = identificadores[i];
                        int a_inicioVar = identificadores[i - 1]; // EN ESTA PARTE ES QUE ASIGNO EL IDENTIFICADOR A LA
                                                                  // VARIABLE
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

                    txt_mensaje.append(
                            "----------------------------------------SINTACTICO--------------------------------------------\n");
                    // TOTAL DE OPERADORES SERÁ LA CANTIDAD DE VARIABLES -1
                    int totaloperadores = var_cont - 1;

                    // ESTE CONDICIONAL VALIDA QUE SE CUMPLA QUE LOS OPERADORES
                    // DEBEN SER IGUAL AL NUMERO DE VARIABLES -1 PARA DAR CUMPLIMIENTO
                    // AL TEMA SINTACTICO DE LAS OPERACIONES
                    if (totaloperadores == contadorOperadores) {
                        txt_mensaje.append("Operadores SI es igual a la cantidad de variables - 1\n");

                        // SI SE CUMPLE LA CONDICIO DADA ARRIBA DE LOS OPERADORES SE ORDENA
                        System.out.println("ahora a ordenar\n");

                        // UNIR LOS ARREGLOS EN UNO SOLO ARREGLO PARA ORDENARLOS
                        // A ESTE ARREGLO LE APLICO UN METODO PARA ELIMINAR LOS ESPACIOS NULL
                        // QUE ME ESTABAN DANDO ERROR

                        String[] nuevoArreglo = unirArreglos(eliminarNull(arregloVariables),
                                eliminarNull(operadoresEncontrados));
                        // txt_mensaje.append("arreglo nuevoArreglo" + Arrays.toString(nuevoArreglo) +
                        // "\n");

                        // for (int j = 0; j < nuevoArreglo.length; j++) {

                        // }
                        // ORDENAR LOS CARACTERES DE FORMA VARIABLE OPERADOR VARIABLE
                        // IMPLEMENTANDO EL CODIGO FACILITADO POR EL ING BORIS Y ADAPTANDOLO
                        // AL CONTEXTO DE ESTE

                        String[] ordenado = new String[nuevoArreglo.length];
                        int j = 0;
                        int n = 1;
                        for (int i = 0; i < nuevoArreglo.length; i++) {

                            String c = (nuevoArreglo[i]);
                            // txt_mensaje.append("VALOR A GUARDAR " + c + "\n");
                            // EN EL ORDENAMIENTO DEL ING BORIS PREGUNTA POR EL PRIMER CARACTER
                            // EN ESTE CASO YA TENGO LAS VARIABLES COMPLETAS CON EL IDENTIFICADOR EN EL
                            // ARREGLO NUEVO
                            // LO QUE HICE FUE INVERTIR LA LOGICA Y PREGUNTAR POR EL OPERADOR.
                            if ((String.valueOf(c).equals("+")) || (String.valueOf(c).equals("-"))
                                    || (String.valueOf(c).equals("*")) || (String.valueOf(c).equals("/"))) {
                                ordenado[n] = nuevoArreglo[i];
                                n = n + 2;
                            } else {

                                ordenado[j] = nuevoArreglo[i];
                                j = j + 2;
                            }

                            // System.out.println("----------------"+nuevoArreglo[j]+"----------------"+ordenado[i]+"----------------");
                        }

                        txt_mensaje.append( "----------------------------------------ORDENAMIENTO--------------------------------------------\n");
                        txt_mensaje.append(Arrays.toString(ordenado) + "\n");

                        System.out.println("arreglo ORDENADO de operadores y tipos de variables");

                        for (int h = 0; h < nuevoArreglo.length; h++) {
                            System.out.println("posición " + h + " valor " + ordenado[h]);

                        }
                        //SI EL SINTACTICO CUMPLE CON LAS REGLAS LE ASIGNO 1 A SU VARIABLE
                        //PARA PERMITIR SEGUIR AL SEMANTICO
                        sintactico = 1;
                        if (sintactico == 1) {
                            txt_mensaje.append( "----------------------------------------SEMANTICO--------------------------------------------\n");
                               txt_mensaje.append(""+Arrays.toString(ordenado)+"\n");

                               for (int i = 0; i < ordenado.length; i++) {
                                int posicion = i;
                                char primeraLetra = idVariables(ordenado, posicion);
                                txt_mensaje.append("\n"+" posición: " + posicion + " es: " + primeraLetra);

                                if(primeraLetra=="$");
                                txt_mensaje.append("siciertowe");
                               }
                              

                        }
                    } else {
                        txt_mensaje.append("Operadores NO es igual a la cantidad de variables - 1\n");
                        
                    }

                }

            }
        } else {
            txt_mensaje.append("" + validarLongitudTexto(texto));
        }

    }// GEN-LAST:event_txt_textoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO EL CODIGO

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
            }
        });
    }

    // METODOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSs
    public static void Limpiar() {

        txt_mensaje.setText("");

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
    public static char idVariables(String[] arregloTextos, int posicion) {

    // Validar la posición
    if (posicion < 0 || posicion >= arregloTextos.length) {
        throw new IllegalArgumentException("Posición invalida: " + posicion);
    }

    // Obtener el texto en la posición indicada
    String texto = arregloTextos[posicion];

    // Obtener la primera letra del texto
    char primeraLetra = texto.charAt(0);

    // Devolver la primera letra
    return primeraLetra;
}

    public static String validarLongitudTexto(String texto) {
        int longitudMaxima = 20;
        int longitudActual = texto.length();
        int caracteresRestantes = longitudMaxima - longitudActual;

        if (longitudActual > longitudMaxima) {
            return "Texto demasiado largo (" + longitudActual + " caracteres). Se permiten máximo " + longitudMaxima
                    + " caracteres.";
        } else {
            return "Te quedan " + caracteresRestantes + " caracteres de los " + longitudMaxima
                    + " disponibles.";
        }
    }

    // public static boolean validarvariable(char[] caracteres) {

    // // Regla adicional: La variable no puede estar vacía
    // if (caracteres.length == 0) {
    // return false;
    // }

    // for (int i = 0; i < caracteres.length; i++) {
    // char c = caracteres[i];

    // // Regla 1: No puede empezar por un valor numérico
    // if (i == 0 && (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c
    // == '5' || c == '6' || c == '7'
    // || c == '8' || c == '9')) {
    // System.out.println("No puede empezar por un valor numérico la variable");
    // return false;
    // }

    // // Regla 2: Debe empezar por una letra
    // if (i == 0 && !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c ==
    // '_')) {
    // System.out.println("Debe empezar por una letra la variable");
    // return false;
    // }

    // // Regla 3: Los caracteres pueden ser letras, dígitos, guiones bajos o
    // // operadores
    // if (!(Character.isLetterOrDigit(c) || c == '_' || c == '+' || c == '-' || c
    // == '*')) {
    // System.out.println(
    // "Los caracteres pueden ser letras, dígitos, guiones bajos o operadores y no
    // puede tener espacio");
    // return false;
    // }

    // }
    // return true;
    // }

    private static boolean esLetra(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_');
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
            txt_mensaje.append("EL TEXTO ES VALIDO\n");

        } else {
            txt_mensaje.append("EL TEXTO NO ES VALIDO\n");

        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton btn_limpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea txt_mensaje;
    public static javax.swing.JTextField txt_texto;
    // End of variables declaration//GEN-END:variables
}
